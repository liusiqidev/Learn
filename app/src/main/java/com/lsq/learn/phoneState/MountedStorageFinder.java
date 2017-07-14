package com.lsq.learn.phoneState;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v4.os.EnvironmentCompat;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 17-6-27.
 */

public class MountedStorageFinder{

    public class StorageInfo{
        public boolean isRemoved;
        public String state;
        public String mountPath;
    }

    private String sdcardPath;
    private String externalPath;

    List<StorageInfo> mstorageInfoList = new ArrayList<StorageInfo>();

    public MountedStorageFinder(Context context){
        final StorageManager storageManager = (StorageManager)context.getSystemService(Context.STORAGE_SERVICE);

        final Class<?>storageValumeClazz;
        final Method getVolumeList;
        final Method getPath;
        Method isRemovable;
        Method mGetState=null;
        try {
            //得到StorageVolume类的对象
            storageValumeClazz = Class.forName("android.os.storage.StorageVolume");
            //得到StorageManager中的getVolumeList()方法的对象
            getVolumeList = storageManager.getClass().getMethod("getVolumeList");

            //获得StorageVolume中的一些方法
            getPath=storageValumeClazz.getMethod("getPath");

            isRemovable=storageValumeClazz.getMethod("isRemovable");

            //getState方法是在4.4_r1之后的版本加的，之前版本（含4.4_r1）没有
            //（http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/4.4_r1/android/os/Environment.java/）

            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT) {

                mGetState = storageValumeClazz.getMethod("getState");
            }


            //反射获取属性的核心方法，最终会得到每个StorageVolume对象的path、removable和state属性。
//调用getVolumeList方法，参数为：“谁”中调用这个方法
            final Object invokeVolumeList = getVolumeList.invoke(storageManager);
            final int length = Array.getLength(invokeVolumeList);
            ArrayList list=new ArrayList<>();
            String state=null;
            for(int i=0;i<length;i++){
                final Object storageValume = Array.get(invokeVolumeList,i);//得到StorageVolume对象
                final String path=(String)getPath.invoke(storageValume);
                final boolean removable=(Boolean)isRemovable.invoke(storageValume);

                if(mGetState!=null){
                    state=(String)mGetState.invoke(storageValume);
                }
                else {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                        state= Environment.getStorageState(new File(path));
                    }else{
                        if(removable){
                            state= EnvironmentCompat.getStorageState(new File(path));
                        }else{
                            //不能移除的存储介质，一直是mounted
                            state=Environment.MEDIA_MOUNTED;
                        }
                        final File externalStorageDirectory=Environment.getExternalStorageDirectory();
                        //Log.e(TAG,"externalStorageDirectory=="+externalStorageDirectory);

                    }
                }

                    /*

                    final String s = state;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //MAlerDialog.newAlertDialog(ScanApkActivity.this,finalMsg);
                            //Toast.makeText(ScanMultiActivity.this, "没有数据！",Toast.LENGTH_LONG).show();
                            TextView textView = (TextView)MainActivity.this.findViewById(R.id.md5show);
                            textView.getText();
                            textView.setText(textView.getText()+"\n"+"可以移除"+removable+" "+s+" "+path);
                        }
                    });
                    */
                if(state.equals("mounted")){

                    StorageInfo si = new StorageInfo();
                    si.isRemoved = removable;
                    si.state = state;
                    si.mountPath = path;

                    mstorageInfoList.add(si);

                }

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<StorageInfo> getStorageList(){
        return mstorageInfoList;
    }

}
