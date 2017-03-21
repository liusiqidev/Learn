package com.lsq.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lsq on 2017/3/18.
 */

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: "+getClass().getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: "+getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: "+getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: "+getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: "+getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+getClass().getSimpleName());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: "+getClass().getSimpleName());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: "+getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: "+getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: "+getClass().getSimpleName());
    }
}
