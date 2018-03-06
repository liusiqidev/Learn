package com.lsq.learn.h5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lsq on 18-3-5.
 */

public class HtmlfAct extends BaseActivity {
    private static final String TAG = "HtmlfAct";

    @BindView(R.id.html_web)
    WebView htmlWeb;
    @BindView(R.id.main_btn)
    Button mainBtn;
    @BindView(R.id.main_tv)
    TextView mainTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_act);
        ButterKnife.bind(this);

        init();
    }

    /**
     * 初始化设置
     *
     * @param
     * @return
     * @author lsq
     * @time 18-3-5
     */
    private void init() {
//        WebSettings webSettings=htmlWeb.getSettings();
//        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();

        htmlWeb.loadUrl("file:///android_asset/main/main.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        htmlWeb.onResume();
//        htmlWeb.pauseTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @OnClick({R.id.main_btn, R.id.main_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn:
                break;
            case R.id.main_tv:
                break;
        }
    }
}
