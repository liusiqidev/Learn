package com.lsq.learn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lsq.learn.QRcode.QRcodeActivity;
import com.lsq.learn.ampm.AmPmActivity;
import com.lsq.learn.baiduMap.BaiduMapActivity;
import com.lsq.learn.broadcast.NetchangActivity;
import com.lsq.learn.check.CheckActivity;
import com.lsq.learn.fragment.FragmentActivity;
import com.lsq.learn.permission.checkPermissionsActivity;
import com.lsq.learn.phoneState.PhoneStateActivity;
import com.lsq.learn.player.PlayerActivity;
import com.lsq.learn.recyclerView.RecyclerActivity;
import com.lsq.learn.newsActivity.NewsActivity;
import com.lsq.learn.save.saveActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends BaseActivity{
    private RecyclerView rec_main;
    private List<String> datas;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datas=new ArrayList<>();
        datas.add("recycleRactivity");//0
        datas.add("fragmentActivity");//1
        datas.add("news");//2
        datas.add("广播");//3
        datas.add("保存文件");//4
        datas.add("百度地图sdk获取位置信息");//5
        datas.add("动态权限");//6
        datas.add("获取手机信息");//7
        datas.add("卸载应用");//8
        datas.add("AmPm");//9
        datas.add("获取版本号，文件下载显示进度");//10
        datas.add("发送短信");//11
        datas.add("扫描二维码");//12
        datas.add("播放器");//13



        rec_main= (RecyclerView) findViewById(R.id.rec_main);
        rec_main.setLayoutManager(new LinearLayoutManager(this));
        rec_main.setAdapter(new MainRecAdapter(datas));
    }
    //卸载应用程序   "package:"+你要卸载的应用程序包名
    public void unstallApp(){
        Intent uninstall_intent = new Intent();
        uninstall_intent.setAction(Intent.ACTION_DELETE);
        uninstall_intent.setData(Uri.parse("package:"+"com.lsq.learn"));
        startActivity(uninstall_intent);
    }


    class MainRecAdapter extends RecyclerView.Adapter<MainRecAdapter.ViewHolder>{
        List<String> datas;
        Intent intent;
        public MainRecAdapter(List<String> datas) {
            this.datas = datas;
        }
        @Override
        public MainRecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
            Button btn= (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
            final ViewHolder holder=new ViewHolder(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("111", "onClick: "+holder.getAdapterPosition());
                    switch (holder.getAdapterPosition()){
                        case 0:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, RecyclerActivity.class);
                            break;
                        case 1:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, FragmentActivity.class);
                            break;
                        case 2:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, NewsActivity.class);
                            break;
                        case 3:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, NetchangActivity.class);
                            break;
                        case 4:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, saveActivity.class);
                            break;
                        case 5:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, BaiduMapActivity.class);
                            break;
                        case 6:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, checkPermissionsActivity.class);
                            break;
                        case 7:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, PhoneStateActivity.class);
                            break;
                        case 8:
                            unstallApp();
                            intent=null;
                            break;
                        case 9:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, AmPmActivity.class);
                            break;
                        case 10:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, CheckActivity.class);
                            break;
                        case 11:
                            intent=null;
                            sendSMS("18210008209","11111111111111");
                            break;
                        case 12:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, QRcodeActivity.class);
                            break;
                        case 13:
                            intent=new Intent();
                            intent.setClass(MainActivity.this, PlayerActivity.class);
                            break;

                    }
                    if (intent!=null)
                        startActivity(intent);
                }
            });
            return holder;
        }
        public void sendSMS(String phoneNumber,String message){
            //获取短信管理器
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            //拆分短信内容（手机短信长度限制）
            List<String> divideContents = smsManager.divideMessage(message);
            for (String text : divideContents) {
                Log.d(TAG, "sendSMS: ");
                smsManager.sendTextMessage(phoneNumber, null, text, null, null);
            }
        }

        @Override
        public void onBindViewHolder(MainRecAdapter.ViewHolder holder, int position) {
            holder.btn.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            Button btn;
            public ViewHolder(View itemView) {
                super(itemView);
                btn= (Button) itemView;
            }
        }
    }
}
