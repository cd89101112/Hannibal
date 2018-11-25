package com.hannibal.scalpel;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.widget.Toast;

import com.hannibal.scalpel.Util.CommonUtils;
import com.hannibal.scalpel.service.BiopsyService;
import com.hannibal.scalpel.task.ExceptionsHandlingService;

import static com.hannibal.scalpel.Constant.DevLogTag;

public class Hannibal extends Application {

    private static Context mContext;
    private static Hannibal handler = new Hannibal();
    private static final int JOB_ID = 1000;

    public static Context getInstance() {
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;

        // 全局捕获Exce
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                ExceptionsHandlingService.handleException(e);
            }
        });

        CommonUtils.printDevLog("init hannibal success");
    }

    public static void testCrash() {
        try {
            String ss = null;
            ss = ss.trim();
        } catch (Exception e) {
            ExceptionsHandlingService.handleException(e);
            CommonUtils.printDevLog( "test crash success!");
        }
    }

}
