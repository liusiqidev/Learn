package com.lsq.learn.SplashAndGuide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.MainActivity;
import com.lsq.learn.R;

/**
 * Created by lsq on 2017/7/2.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        SharedPreferences shared=getSharedPreferences("is", MODE_PRIVATE);
        boolean isfer=shared.getBoolean("isfer", true);
        SharedPreferences.Editor editor=shared.edit();
        // 如果是第一次启动，则先进入功能引导页
        if (isfer) {
            editor.putBoolean("isfer",false);
            editor.commit();
            Intent intent = new Intent(this, Guideactivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
//        setContentView(R.layout.activity_splash);

//        new Handlerer().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
                enterHomeActivity();
//            }
//        }, 2000);
    }
    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
