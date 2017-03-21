package com.lsq.learn.newsActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.BaseFragment;
import com.lsq.learn.R;

import java.util.zip.Inflater;

public class NewsActivity extends BaseActivity {
    NewsTitleFragment titleFragment;
    NewsContentFragment contentFragment;
    String title, content;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        titleFragment = new NewsTitleFragment();
        contentFragment = new NewsContentFragment();
        addFragment(contentFragment);
        addFragment(titleFragment);
    }

    public void chengContentFrag(String title, String content) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.show(contentFragment);
        transaction.hide(titleFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        i=1;
        this.content = content;
        this.title = title;
        contentFragment.refresh(title, content);
    }

    @Override
    public void onBackPressed() {
        if (i==1){
            Log.d("zzz", "onBackPressed: ");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.show(titleFragment);
            transaction.hide(contentFragment);
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            transaction.commit();
            i=0;
        }else
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
//            if (getIntent().getStringExtra("news_title")!=null)
//            contentFragment.refresh(title,content);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.FL_news_act, fragment);
        transaction.commit();
    }

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent();
        intent.setClass(context, NewsActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("new_content", newsContent);
        context.startActivity(intent);
    }
}
