package com.apping.baisu.demo.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.apping.baisu.demo.application.MyApplication;

/**
 * Created by baisu on 14-12-21.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;

    public int activityState;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    public abstract void initWidget();

    public abstract void widgetClick(View v);

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        MyApplication.getInstance().addActivity(this);
        initWidget();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
    }

    @Override
    protected void onStop() {
        super.onResume();
        activityState = ACTIVITY_STOP;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
        MyApplication.getInstance().finishActivity(this);
    }
}
