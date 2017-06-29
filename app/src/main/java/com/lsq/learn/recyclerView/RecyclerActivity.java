package com.lsq.learn.recyclerView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends BaseActivity {
    private List<Student> students=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initStudents();
        /**
         * 使用前需要先在Glade compile 'com.android.support:recyclerview-v7:25.2.0'
         */
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //水平竖直LinearLayoutManager网格GridLayoutManager瀑布流StaggeredGridLayoutManager
                                                                                            //第一个参数是竖直是列数，水平是行数，
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //设置滚动方向
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        mRecyclerAdapter adapter=new mRecyclerAdapter(students);
        recyclerView.setAdapter(adapter);

    }

    private void initStudents() {
        for (int i=0;i<30;i++)
        students.add(new Student("学生"+i,i));
    }
}
