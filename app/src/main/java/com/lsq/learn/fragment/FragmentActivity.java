package com.lsq.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

/**
 * Created by lsq on 2017/3/17.
 */

public class FragmentActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_fragmentac;
    private LeftFragment leftFragment;
    private RightFragment rightFragment;
    private int FRAGMENT_POSITION=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        addFragment();
        btn_fragmentac = (Button) findViewById(R.id.btn_fragmentac);
        btn_fragmentac.setOnClickListener(this);
    }

    private void replaceFragment(Fragment fragment, String Tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.FL_fragmentac, fragment, Tag);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragmentac:
                changeFragment(changepage());
                break;
        }
    }
    private int changepage(){
        if (FRAGMENT_POSITION==0){
            FRAGMENT_POSITION=1;
        }else {
            FRAGMENT_POSITION=0;
        }
        return FRAGMENT_POSITION;
    }

    private void changeFragment(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (position) {
            case 0:
                transaction.show(leftFragment);
                transaction.hide(rightFragment);
                break;
            case 1:
                transaction.show(rightFragment);
                transaction.hide(leftFragment);
                break;
        }
        transaction.commit();
    }

    private void addFragment() {
        leftFragment = new LeftFragment();
        rightFragment = new RightFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.FL_fragmentac, rightFragment, "rightfragment");
        transaction.add(R.id.FL_fragmentac, leftFragment, "leftfragment");
        //添加fragment的栈，返回键，返回上一个fragment
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
