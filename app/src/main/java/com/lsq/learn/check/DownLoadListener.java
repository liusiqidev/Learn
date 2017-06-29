package com.lsq.learn.check;

/**
 * Created by lsq on 2017/6/29.
 */

public interface DownLoadListener {
    //通知下载进度
    void onProgress(int progress);
    //通知下载成功
    void onSuccess();
    //通知下载失败
    void onFailed();
    //通知下载暂停
    void onPaused();
    //通知下载取消
    void onCanceled();
}
