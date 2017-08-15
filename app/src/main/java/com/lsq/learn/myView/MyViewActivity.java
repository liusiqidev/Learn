package com.lsq.learn.myView;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.myView.mView.GameView;

/**
 * Created by lsq on 2017/8/15.
 */

public class MyViewActivity extends BaseActivity {
    private static final int REFRESH=0x000001;
    private GameView mgameView=null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
