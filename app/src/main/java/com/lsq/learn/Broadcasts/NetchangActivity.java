package com.lsq.learn.Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

/**
 * Created by lsq on 2017/3/21.
 */

public class NetchangActivity extends BaseActivity {
    private IntentFilter intentFilter;
    private NetworkchangeReceiver networkchangeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bro_netchange);
        Button btn_act_bro_netchang= (Button) findViewById(R.id.btn_act_bro_netchang);
        btn_act_bro_netchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.lsq.learn.broadcasttest.MY_BROADCAST");
                //发送标准广播
                sendBroadcast(intent);
                //发送有序广播
                sendOrderedBroadcast(intent,null);
                //发送
            }
        });
        intentFilter = new IntentFilter();
        //广播过滤器，                     监听网络变化
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkchangeReceiver = new NetworkchangeReceiver();
        registerReceiver(networkchangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkchangeReceiver);
        super.onDestroy();
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
