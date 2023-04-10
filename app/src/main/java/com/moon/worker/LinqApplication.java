package com.moon.worker;

import android.app.Application;


public class LinqApplication extends Application {

    public static LinqApplication application;


    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化涂鸦SDK
        application = this;
    }

}
