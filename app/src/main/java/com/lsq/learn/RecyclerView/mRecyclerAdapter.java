package com.lsq.learn.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lsq on 2017/3/7.
 * 将泛型改为当前类的内部类ViewHolder。
 */

public class mRecyclerAdapter extends RecyclerView.Adapter<mRecyclerAdapter.ViewHolder> {

    static class ViewHolder extends  RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    //用于创造ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
    //对子项赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
    //子项数量
    @Override
    public int getItemCount() {
        return 0;
    }
}
