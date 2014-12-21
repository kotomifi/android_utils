package com.apping.baisu.demo.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.apping.baisu.demo.activity.BaseActivity;

import java.util.Stack;

/**
 * Created by baisu on 14-12-21.
 */
public class MyApplication extends Application {

    private static Stack<BaseActivity> activityStack;
    private static MyApplication instance;

    private MyApplication() {

    }

    /**
     * 单例，无需考虑多线程
     * @return
     */
    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    /**
     * 添加 Activity 到栈
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<BaseActivity>();
        }
        activityStack.push(activity);
    }

    /**
     * 获取当前 Activity(栈顶Activity)
     * @return
     */
    public BaseActivity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        BaseActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        BaseActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定Activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 完成除了指定Activity以外的所有Activity,如果cls不在栈中，则栈清空
     * @param cls
     */
    public void finishOtherActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (!(activity.getClass().equals(cls))) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     * @param context
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}
