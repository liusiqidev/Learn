package com.lsq.learn.newsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsq.learn.BaseFragment;
import com.lsq.learn.R;

/**
 * Created by lsq on 2017/3/18.
 */

public class NewsTitleFragment extends BaseFragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_news_title,container,false);
        return view;
    }
}
