package com.lsq.learn;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by lsq on 2017/3/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: "+getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: "+getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+getClass().getSimpleName());
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+getClass().getSimpleName());
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: "+getClass().getSimpleName());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        Log.d(TAG, "onDestroy: "+getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: "+getClass().getSimpleName());
    }

}
