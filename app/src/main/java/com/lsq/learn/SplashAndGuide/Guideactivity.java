package com.lsq.learn.SplashAndGuide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lsq.learn.BaseActivity;

/**
 * Created by lsq on 2017/7/2.
 */

public class Guideactivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t=new TextView(this);
        t.setText("äaaa");
        setContentView(t);
    }
}
