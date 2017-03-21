package com.lsq.learn.newsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lsq.learn.BaseFragment;
import com.lsq.learn.R;

import java.util.zip.Inflater;

/**
 * Created by lsq on 2017/3/18.
 */

public class NewsContentFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseFragment", "onCreateView: "+getClass().getSimpleName());
        view = inflater.inflate(R.layout.frag_news_content, container, false);
        return view;
    }

    public void refresh(String newsTitle, String newsContent) {
        TextView title = (TextView) view.findViewById(R.id.TV_title);
        TextView content = (TextView) view.findViewById(R.id.TV_content);
        title.setText(newsTitle);
        content.setText(newsContent);
    }
}
