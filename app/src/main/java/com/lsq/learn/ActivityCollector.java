package com.lsq.learn;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2017/3/7.
 */

public class ActivityCollector {
    private List<Activity> activities=new ArrayList<>();
    private static ActivityCollector instance;
    public static ActivityCollector getInstance(){
        if (instance==null){
            synchronized (ActivityCollector.class){
                if (instance==null){
                    instance=new ActivityCollector();
                }
            }
        }
        return instance;
    }
    private  ActivityCollector() {
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }
    public void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public void finishall(){
        for (Activity activity:activities){
            if (!activity.isFinishing())
                activity.finish();
        }
    }
}
