package com.lsq.learn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by lsq on 2017/3/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private IntentFilter intentFilter;
    private ForOfflineReceive forOfflineReceive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        ActivityCollector.getInstance().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + getClass().getSimpleName());
        //动态添加广播接收器（用来强行退出）
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.lsq.learn.broadcast.FORCE_OFFLINE");
        forOfflineReceive = new ForOfflineReceive();
        registerReceiver(forOfflineReceive, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(forOfflineReceive);
        super.onPause();
        Log.d(TAG, "onPause: " + getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.getInstance().removeActivity(this);
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + getClass().getSimpleName());
    }

    class ForOfflineReceive extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            //弹出一个不可取消的提示框，确定后返回主界面
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("warning")
                    .setMessage("返回主界面")
                    //设置不能返回
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCollector.getInstance().finishall();
                            Intent intent = new Intent();
                            intent.setClass(context, MainActivity.class);
                            context.startActivity(intent);
                        }
                    }).show();


        }
    }
}
