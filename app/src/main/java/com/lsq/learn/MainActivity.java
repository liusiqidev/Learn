package com.lsq.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lsq.learn.Broadcasts.NetchangActivity;
import com.lsq.learn.Fragment.FragmentActivity;
import com.lsq.learn.RecyclerView.RecyclerActivity;
import com.lsq.learn.newsActivity.NewsActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4= (Button) findViewById(R.id.btn4);
        btn5= (Button) findViewById(R.id.btn5);
        btn6= (Button) findViewById(R.id.btn6);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn1:
                intent=new Intent();
                intent.setClass(this, RecyclerActivity.class);
                break;
            case R.id.btn2:
                intent=new Intent();
                intent.setClass(this,FragmentActivity.class);
                break;
            case R.id.btn3:
                intent=new Intent();
                intent.setClass(this, NewsActivity.class);
                break;
            case R.id.btn4:
                intent=new Intent();
                intent.setClass(this, NetchangActivity.class);
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
        }
        if (intent!=null)
        startActivity(intent);
    }
}
