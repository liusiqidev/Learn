package com.lsq.learn.newsActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lsq.learn.R;

public class NewsContentActivity extends AppCompatActivity {
    NewsContentFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        fragment=new NewsContentFragment();
        replaceFragment(fragment);
        Intent intent=getIntent();
        String newsTitle=intent.getStringExtra("news_title");
        String newsContent=intent.getStringExtra("news_content");
        fragment.refresh(newsTitle,newsContent);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.FL_news_act,fragment);
        transaction.commit();
    }

    public void actionStart(Context context,String newsTitle,String newsContent){
        Intent intent=new Intent();
        intent.setClass(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("new_content",newsContent);
        startActivity(intent);
    }
}
