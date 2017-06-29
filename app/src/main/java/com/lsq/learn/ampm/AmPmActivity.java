package com.lsq.learn.ampm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.ButtonActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2017/6/26.
 */

public class AmPmActivity extends ButtonActivity{
    List<String> data=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        data.add("pm删除应用");
        setdatas(data);
        super.onCreate(savedInstanceState);
        setResponse(new Response() {
            @Override
            public void response(int i) {
                switch (i){
                    case 0:

                        showResults("111");
                        break;
                }
            }
        });
    }

    private void runShellCommand(String command) {
        Process process = null;
        BufferedReader bufferedReader = null;
        StringBuilder mShellCommandSB =new StringBuilder();
        Log.d("wenfeng", "runShellCommand :" + command);
        mShellCommandSB.delete(0, mShellCommandSB.length());
        String[] cmd = new String[] { "/system/bin/sh", "-c", command }; //调用bin文件
        try {
            byte b[] = new byte[1024];
            process = Runtime.getRuntime().exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                mShellCommandSB.append(line);
            }
            Log.d("wenfeng", "runShellCommand result : " + mShellCommandSB.toString());
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }

            if (process != null) {
                process.destroy();
            }
        }
    }


}
