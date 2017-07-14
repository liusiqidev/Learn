package com.lsq.learn.player;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.ButtonActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2017/7/5.
 */

public class PlayerActivity extends ButtonActivity {
    private List<String> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        data=new ArrayList<>();
        data.add("播放文件");
        setdatas(data);
        super.onCreate(savedInstanceState);
        setResponse(new Response() {
            @Override
            public void response(int i) {
                switch (i){
                    case 0:
                        playMp4();
                        break;
                }
            }
        });
    }
    private void playMp4(){
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Download/123.mp4");
        //调用系统自带的播放器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.v("URI:::::::::", uri.toString());
        intent.setDataAndType(uri, "video/mp4");
        startActivity(intent);
    }
}
