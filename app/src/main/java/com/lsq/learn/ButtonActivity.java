package com.lsq.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lsq.learn.baiduMap.BaiduMapActivity;
import com.lsq.learn.broadcast.NetchangActivity;
import com.lsq.learn.fragment.FragmentActivity;
import com.lsq.learn.newsActivity.NewsActivity;
import com.lsq.learn.permission.checkPermissionsActivity;
import com.lsq.learn.phoneState.PhoneStateActivity;
import com.lsq.learn.recyclerView.RecyclerActivity;
import com.lsq.learn.save.saveActivity;

import java.util.List;

/**
 * Created by lsq on 2017/6/26.
 */

public abstract class ButtonActivity extends BaseActivity {
    private static List<String> mdatas;
    private RecyclerView rec_button;
    private TextView TV_button;
    private Response mresponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        rec_button= (RecyclerView) findViewById(R.id.rec_button);
        TV_button= (TextView) findViewById(R.id.TV_button);
        rec_button.setLayoutManager(new LinearLayoutManager(this));
        rec_button.setAdapter(new ButtonAdapter(mdatas));
    }

    /**   setdatas
     *  该方法需要在onCreate的super.onCreate方法前执行
     * @param datas
     */

    public static void setdatas(List<String> datas) {
        mdatas = datas;
    }

    public interface Response {
        void response(int i);
    }

    public void setResponse(Response response) {
        mresponse = response;
    }
    public void showResults(String s){
        if (!s.isEmpty()&&s!=null)
            TV_button.setText(s);
    }
    class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder>{
        List<String> datas;
        public ButtonAdapter(List<String> datas) {
            this.datas = datas;
        }
        @Override
        public ButtonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Button btn= (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
            final ViewHolder holder=new ViewHolder(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("111", "onClick: "+holder.getAdapterPosition());
                    mresponse.response(holder.getAdapterPosition());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ButtonAdapter.ViewHolder holder, int position) {
            holder.btn.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Button btn;
            public ViewHolder(View itemView) {
                super(itemView);
                btn= (Button) itemView;
            }
        }
    }


}
