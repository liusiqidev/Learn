package com.lsq.learn.SplashAndGuide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.MainActivity;

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
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guideactivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
