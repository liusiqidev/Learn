package com.lsq.learn.check;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.lsq.learn.MainActivity;
import com.lsq.learn.R;

import java.io.File;

/**
 * Created by lsq on 2017/6/29.
 */

public class DownloadService extends Service {
    private DownLoadTask downLoadTask;
    private String downloadUrl;
    private static final String TAG = "DownloadService";
    private static callBack callBack;
    private DownLoadListener listener = new DownLoadListener() {

        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
            if (callBack != null) {
                callBack.onServiceProgress(progress);
            }
        }

        @Override
        public void onSuccess() {
            downLoadTask = null;
            //下载成功时将前台的服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
            if (callBack != null) {
                callBack.onServiceSuccess();
            }
        }

        @Override
        public void onFailed() {
            downLoadTask = null;
            //下载成功时将前台的服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
            if (callBack != null) {
                callBack.onServiceFailed();
            }
        }

        @Override
        public void onPaused() {
            downLoadTask = null;
            Toast.makeText(DownloadService.this, "Paused", Toast.LENGTH_SHORT).show();
            if (callBack != null) {
                callBack.onServicePaused();
            }
        }

        @Override
        public void onCanceled() {
            downLoadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
            if (callBack != null) {
                callBack.onServiceCanceled();
            }
        }
    };
    private DownLoadBinder mBinder = new DownLoadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownLoadBinder extends Binder {
        public void startDownload(String url) {
            Log.d(TAG, "startDownload: ");
            if (downLoadTask == null) {
                downloadUrl = url;
                downLoadTask = new DownLoadTask(listener);
                downLoadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

        public DownloadService getService() {
            return DownloadService.this;
        }

        public void pauseDownload() {
            if (downLoadTask != null) {
                downLoadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downLoadTask != null) {
                downLoadTask.chacelDownload();
            } else {
                if (downloadUrl != null) {
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, CheckActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }


    public  interface callBack {
         void onServiceProgress(int progress);

         void onServiceSuccess();

         void onServicePaused();

         void onServiceFailed();

         void onServiceCanceled();
    }

    public void setCallBack(callBack callBack) {
        this.callBack = callBack;
    }

}
