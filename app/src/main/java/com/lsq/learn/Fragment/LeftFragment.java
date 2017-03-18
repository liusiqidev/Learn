package com.lsq.learn.Fragment;

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

public class LeftFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_left,container,false);
        return view;
    }
}
