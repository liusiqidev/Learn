package com.lsq.learn.phoneState;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.MainActivity;
import com.lsq.learn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2017/6/24.
 */

public class PhoneStateActivity extends BaseActivity {
    private RecyclerView rec_phonestate;
    private List<String> datas;
    private TextView TV_pnonestate;//用来显示获取到的信息
    private Handler handler;
    private static final String TAG = "PhoneStateActivity";
    private PhoneInfo phoneInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonestate);
        datas=new ArrayList<>();
        datas.add("获取手机号");
        TV_pnonestate= (TextView) findViewById(R.id.TV_pnonestate);
        rec_phonestate= (RecyclerView) findViewById(R.id.rec_phonestate);
        rec_phonestate.setLayoutManager(new LinearLayoutManager(this));
        rec_phonestate.setAdapter(new PhoneStateRecAdapter(datas));
        //显示获取到的信息   key：val
        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                TV_pnonestate.setText(msg.getData().getString("val"));
                return false;
            }
        });
    }

    class PhoneStateRecAdapter extends RecyclerView.Adapter<PhoneStateRecAdapter.ViewHolder>{
        List<String> datas;
        public PhoneStateRecAdapter(List<String> datas) {
            this.datas = datas;
        }

        @Override
        public PhoneStateRecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Button btn= (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
            final ViewHolder holder=new ViewHolder(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (holder.getAdapterPosition()){
                        case 0:
                            phoneInfo=new PhoneInfo(PhoneStateActivity.this);
                            phoneInfo.checkisDouble();
                            break;
                    }
                }
            });

            return holder;
        }


        private void changeTV_pnonestateContent(String content){
            Message msg=new Message();
            Bundle bundle=new Bundle();
            bundle.putString("val",content);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        @Override
        public void onBindViewHolder(PhoneStateRecAdapter.ViewHolder holder, int position) {
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
