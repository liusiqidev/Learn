package com.lsq.learn.myView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;
import com.lsq.learn.myView.mView.GameView;
import com.lsq.learn.myView.mView.Job07View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsq on 2017/8/15.
 */

public class MyViewActivity extends BaseActivity {
    private static final int REFRESH = 0x000001;
    @BindView(R.id.view)
    Job07View view;
    EditText
    private GameView mgameView = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myview);
        ButterKnife.bind(this);

        view.animate().start();
    }
}
