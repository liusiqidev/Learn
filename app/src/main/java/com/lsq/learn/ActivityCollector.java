package com.lsq.learn;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2017/3/7.
 */

public class ActivityCollector {
    private static List<Activity> activities=new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishall(){
        for (Activity activity:activities){
            if (!activity.isFinishing())
                activity.finish();
        }
    }
}
