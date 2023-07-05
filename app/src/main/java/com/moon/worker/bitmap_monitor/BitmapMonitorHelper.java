package com.moon.worker.bitmap_monitor;

import android.content.Context;
import android.os.Debug;

import com.moon.worker.BuildConfig;

import top.shixinzhang.bitmapmonitor.BitmapMonitor;

/**
 * @Des：
 * @author: moon
 * @date: 4/14/23
 */
public class BitmapMonitorHelper {

    public static void init(Context context){
        long checkInterval = 10;
        long threshold = 100 * 1024;
        long restoreImageThreshold = 100 * 1024;;
        String dir = context.getExternalFilesDir("bitmap_monitor").getAbsolutePath();

        boolean isDebug = BuildConfig.DEBUG;

        BitmapMonitor.Config config = new BitmapMonitor.Config.Builder()
                .checkRecycleInterval(checkInterval)    //检查图片是否被回收的间隔，单位：秒 （建议不要太频繁，默认 5秒）
                .getStackThreshold(threshold)           //获取堆栈的阈值，当一张图片占据的内存超过这个数值后就会去抓栈
                .restoreImageThreshold(restoreImageThreshold)   //还原图片的阈值，当一张图占据的内存超过这个数值后，就会还原出一张原始图片
                .restoreImageDirectory(dir)             //保存还原后图片的目录
                .showFloatWindow(isDebug)                  //是否展示悬浮窗，可实时查看内存大小（建议只在 debug 环境打开）
                .isDebug(isDebug)
                .context(context)
                .build();

        BitmapMonitor.init(config);
    }

}
