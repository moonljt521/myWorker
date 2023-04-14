package com.moon.worker;

import android.app.Application;

import com.moon.worker.bitmap_monitor.BitmapMonitorHelper;

import top.shixinzhang.bitmapmonitor.BitmapMonitor;

public class LinqApplication extends Application {

    public static LinqApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化涂鸦SDK
        application = this;

        BitmapMonitorHelper.init(this);

        BitmapMonitor.start();
    }

}
