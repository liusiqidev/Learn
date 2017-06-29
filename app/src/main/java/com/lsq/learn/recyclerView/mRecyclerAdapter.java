package com.lsq.learn.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lsq.learn.R;

import java.util.List;

/**
 * Created by lsq on 2017/3/7.
 * 将泛型改为当前类的内部类ViewHolder。
 */

public class mRecyclerAdapter extends RecyclerView.Adapter<mRecyclerAdapter.ViewHolder> {
    List<Student> students;

    static class ViewHolder extends  RecyclerView.ViewHolder{
        View itemview;
        TextView recycler_name;
        TextView recycler_age;
        public ViewHolder(View view) {
            super(view);
            itemview=view;
            recycler_age= (TextView) view.findViewById(R.id.recycler_age);
            recycler_name= (TextView) view.findViewById(R.id.recycler_name);
        }
    }

    public mRecyclerAdapter(List<Student> students) {
        this.students = students;
    }

    //用于创造ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    //对子项赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Student student=students.get(position);
        holder.recycler_name.setText(student.getName());
        holder.recycler_age.setText(String.valueOf(student.getAge()));
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "item"+position+" is checked", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //子项数量
    @Override
    public int getItemCount() {
        return students.size();
    }
}
