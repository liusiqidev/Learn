package com.lsq.learn.phoneState;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by lsq on 2017/6/25.
 */

public class PhoneInfo {
    private static final String TAG = "PhoneInfo";
    private String SIMCARD_1 = "SIMCARD_1";
    private String SIMCARD_2 = "SIMCARD_2";
    private String SIMCARD = "SIMCARD";
    private String ISDOUBLE = "isDouble";
    //电话号码  双卡手机有两个
    private String phoneNumber1;
    private String phoneNumber2;
    //手机设备识别码 双卡手机有两个
    private String IMEI1;
    private String IMEI2;
    //国际移动用户识别码 双卡手机有两个
    private String IMSI1;
    private String IMSI2;

    private TelephonyManager telephonyManager;
    private Context context;
    private Method method;
    private boolean isDouble = true;
    private Object result_0 = null;
    private Object result_1 = null;

    public PhoneInfo(Context context) {
        this.context = context;
    }

    public void checkisDouble() {
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            // 只要在反射getSimStateGemini 这个函数时报了错就是单卡手机（这是我自己的经验，不一定全正确）
            method = TelephonyManager.class.getMethod("getSimStateGemini",
                    new Class[]{int.class});
            // 获取SIM卡1
            result_0 = method.invoke(telephonyManager, new Object[]{new Integer(0)});
            // 获取SIM卡1
            result_1 = method.invoke(telephonyManager, new Object[]{new Integer(1)});
        } catch (SecurityException e) {
            isDouble = false;
            e.printStackTrace();
            // System.out.println("1_ISSINGLETELEPHONE:"+e.toString());
        } catch (NoSuchMethodException e) {
            isDouble = false;
            e.printStackTrace();
            // System.out.println("2_ISSINGLETELEPHONE:"+e.toString());
        } catch (IllegalArgumentException e) {
            isDouble = false;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            isDouble = false;
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            isDouble = false;
            e.printStackTrace();
        } catch (Exception e) {
            isDouble = false;
            e.printStackTrace();
            // System.out.println("3_ISSINGLETELEPHONE:"+e.toString());
        }
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        if (isDouble) {
            // 保存为双卡手机
            editor.putBoolean(ISDOUBLE, true);

            // 保存双卡是否可用
            // 如下判断哪个卡可用.双卡都可以用
            if (result_0.toString().equals("5") && result_1.toString().equals("5")) {
                if (!sp.getString(SIMCARD, "2").equals("0") && !sp.getString(SIMCARD, "2").equals("1")) {
                    editor.putString(SIMCARD, "0");
                }
                editor.putBoolean(SIMCARD_1, true);
                editor.putBoolean(SIMCARD_2, true);
            } else if (!result_0.toString().equals("5") && result_1.toString().equals("5")) {// 卡二可用
                if (!sp.getString(SIMCARD, "2").equals("0") && !sp.getString(SIMCARD, "2").equals("1")) {
                    editor.putString(SIMCARD, "1");
                }
                editor.putBoolean(SIMCARD_1, false);
                editor.putBoolean(SIMCARD_2, true);
            } else if (result_0.toString().equals("5") && !result_1.toString().equals("5")) {// 卡一可用
                if (!sp.getString(SIMCARD, "2").equals("0") && !sp.getString(SIMCARD, "2").equals("1")) {
                    editor.putString(SIMCARD, "0");
                }
                editor.putBoolean(SIMCARD_1, true);
                editor.putBoolean(SIMCARD_2, false);
            } else {// 两个卡都不可用(飞行模式会出现这种种情况)
                editor.putBoolean(SIMCARD_1, false);
                editor.putBoolean(SIMCARD_2, false);
            }
        } else {
            // 保存为单卡手机
            editor.putString(SIMCARD, "0");
            editor.putBoolean(ISDOUBLE, false);
        }
        editor.commit();
        Log.d(TAG, "checkisDouble: "+sp.getString(SIMCARD,null));
        Log.d(TAG, "checkisDouble: "+sp.getBoolean(ISDOUBLE,false));
        Log.d(TAG, "checkisDouble: "+sp.getBoolean(SIMCARD_1,false));
        Log.d(TAG, "checkisDouble: "+sp.getBoolean(SIMCARD_2,false));
        Log.d(TAG, "checkisDouble: "+result_0);
        Log.d(TAG, "checkisDouble: "+result_1);
        Log.d(TAG, "checkisDouble: "+telephonyManager.getDeviceId());



    }

}

//    这里困扰了很多的开发者，可能是错的，希望能提供参考。
//        Class<?> smsManagerClass = null;
//        Class[] divideMessagePamas = {String.class};
//        Class[] sendMultipartTextMessagePamas = {String.class,
//                String.class, ArrayList.class, ArrayList.class,
//                ArrayList.class, int.class};
//        Method divideMessage = null;
//        Method sendMultipartTextMessage = null;
//        smsManagerClass = Class.forName("android.telephony.SmsManager");
//        Method method = smsManagerClass.getMethod("getDefault", new Class[]{});
//        Object smsManager = method.invoke(smsManagerClass, new Object[]{});
//        divideMessage = smsManagerClass.getMethod("divideMessage",
//                divideMessagePamas);
//        sendMultipartTextMessage = smsManagerClass.getMethod(
//                "sendMultipartTextMessage", sendMultipartTextMessagePamas);
//        ArrayList<String> magArray = (ArrayList<String>) divideMessage
//                .invoke(smsManager, content);
//
//        sendMultipartTextMessage.invoke(smsManager, phone, "", magArray,
//                null, null, phoneType);
