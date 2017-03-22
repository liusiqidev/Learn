package com.lsq.learn.Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lsq.learn.MainActivity;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,intent.getAction().toString(),Toast.LENGTH_SHORT).show();
//        Toast.makeText(context,"Boot Complete",Toast.LENGTH_SHORT).show();
//        throw new UnsupportedOperationException("Not yet implemented");

    }
}
