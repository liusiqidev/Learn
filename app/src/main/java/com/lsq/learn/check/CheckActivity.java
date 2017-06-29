package com.lsq.learn.check;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lsq.learn.ButtonActivity;
import com.lsq.learn.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lsq on 2017/6/29.
 */

public class CheckActivity extends ButtonActivity {
    private List<String> datas;
    private int versionCode;// 版本号
    private String versionName;// 版本名
    private AlertDialog.Builder dialog;//更新的对话框
    private static final int LOADMAIN = 1;// 加载主界面
    private static final int SHOWUPDATEDIALOG = 2;// 显示是否更新的对话框
    protected static final int ERROR = 3;// 错误统一代号
//    private NumberProgressBar pb_download;// 下载最新版本apk的进度条

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
// 处理消息
            switch (msg.what) {

                case LOADMAIN:// 加载主界面
                    //loadMain();//进入下一个页面
                    break;
                case ERROR:// 有异常
                    switch (msg.arg1) {
                        case 404:// 资源找不到
                            Toast.makeText(getApplicationContext(), "404资源找不到", Toast.LENGTH_LONG)
                                    .show();
                            break;
                        case 4001:// 找不到网络
                            Toast.makeText(getApplicationContext(), "4001没有网络", Toast.LENGTH_LONG)
                                    .show();
                            break;
                        case 4003:// json格式错误
                            Toast.makeText(getApplicationContext(), "4003json格式错误", Toast.LENGTH_LONG)
                                    .show();
                            break;
                        default:
                            break;
                    }
//                    loadMain();// 进入下一个页面

                    break;
                case SHOWUPDATEDIALOG:// 显示更新版本的对话框
                    showUpdateDialog();
                    break;


                default:
                    break;
            }

            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        datas.add("获取自身版本");
        datas.add("检查服务器版本");
        datas.add("提示更新");
        datas.add("下载更新显示进度");
        setdatas(datas);
        super.onCreate(savedInstanceState);

        setResponse(new Response() {
            @Override
            public void response(int i) {
                switch (i) {
                    case 0:
                        showResults(getLocalVersion());
                        break;
                    case 1:
                        getServiceVersion();
                        break;
                    case 2:
                        break;
                    case 3:
                        downloadAPK();
                        break;
                }
            }
        });

    }

    private void downloadAPK() {
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(""));

    }

    private void getServiceVersion(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://192.168.101.162:7999/service/serviceApk/get")
                .get()
                .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    if (response.isSuccessful()) {
                         final String data = response.body().string();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showResults(data);
                            }
                        });
                    }
                }
            });

    }
    private String getLocalVersion() {
        // 获取自己的版本信息
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            // 版本号
            versionCode = packageInfo.versionCode;
            // 版本名
            versionName = packageInfo.versionName;


        } catch (PackageManager.NameNotFoundException e) {
            // can not reach 异常不会发生
        }
        return versionName+"///"+versionCode;
    }

    private void showUpdateDialog() {
        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false)
                .setTitle("提示")
                .setMessage("检查到新版本，是否更新？")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        downLoadNewApk();// 下载新版本
//                        createFloatView();
//                        dialog.cancel();
//                        loadMain();
//                        pb_download.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //                    loadMain();// 进入下一个页面
                    }
                })
                .show();
    }


}
