package com.lsq.learn.save;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by lsq on 2017/3/23.
 */

public class saveActivity extends BaseActivity {
    EditText ET_save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_save_save);
        ET_save= (EditText) findViewById(R.id.ET_save);
    }

    @Override
    protected void onDestroy() {
        save(ET_save.getText().toString());
        super.onDestroy();
    }

    private void save(String data) {
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        String mdata = data;
        try {
            fos = openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(mdata);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
