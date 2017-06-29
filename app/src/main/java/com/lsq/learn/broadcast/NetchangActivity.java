package com.lsq.learn.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lsq.learn.ActivityCollector;
import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

/**
 * Created by lsq on 2017/3/21.
 */

public class NetchangActivity extends BaseActivity {
    private IntentFilter intentFilter;
    private NetworkchangeReceiver networkchangeReceiver;
    //本地广播
    private LocalReceive localReceive;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bro_netchange);
        Button btn_act_bro_netchang= (Button) findViewById(R.id.btn_act_bro_netchang);
        Button btn_act_bro_local= (Button) findViewById(R.id.btn_act_bro_local);
        Button btn_act_bro_offline= (Button) findViewById(R.id.btn_act_bro_offline);

        btn_act_bro_netchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.lsq.learn.broadcasttest.MY_BROADCAST");
                //发送标准广播
                sendBroadcast(intent);
                //发送有序广播
                sendOrderedBroadcast(intent,null);

            }
        });
        //获取本地广播管理者实例
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        btn_act_bro_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.lsq.learn.broadcast.MY_LOCAL_BROADCAST");
                //发送本地广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        btn_act_bro_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.lsq.learn.broadcast.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

        //本地广播接收器只能动态注册
        intentFilter = new IntentFilter();
        localReceive=new LocalReceive();
        intentFilter.addAction("com.lsq.learn.broadcast.MY_LOCAL_BROADCAST");
        localBroadcastManager.registerReceiver(localReceive,intentFilter);
        //动态注册广播
        intentFilter = new IntentFilter();
        //广播过滤器，                     监听网络变化
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkchangeReceiver = new NetworkchangeReceiver();

        registerReceiver(networkchangeReceiver, intentFilter);
    }

    /**
     * 动态注册的广播需要手动取消注册
     */
    @Override
    protected void onDestroy() {
        unregisterReceiver(networkchangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceive);
        super.onDestroy();
    }
    class  ForOfflineReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ActivityCollector.finishall();
        }
    }
    class  LocalReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,intent.getAction().toString(),Toast.LENGTH_SHORT).show();
        }
    }
    class NetworkchangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //系统服务类，获取当前网络状况
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unacailable", Toast.LENGTH_SHORT).show();
            }


        }

    }

}
