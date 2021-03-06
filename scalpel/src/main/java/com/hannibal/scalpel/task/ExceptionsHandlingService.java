package com.hannibal.scalpel.task;

import android.util.Log;

import com.hannibal.scalpel.BuildConfig;
import com.hannibal.scalpel.Constant;
import com.hannibal.scalpel.Hannibal;
import com.hannibal.scalpel.Util.CommonUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ExceptionsHandlingService {

    /**
     * Display exception to log console if in development mode.
     * If param reportToServer is true, report the exception to backend server.
     *
     * @param exception
     *
     */

    public static void handleException(Throwable exception) {
        if (exception == null || shouldExceptionBeIgnored(exception)) {
            return;
        }

        if (!BuildConfig.DEBUG) {
            Log.e(Constant.DevLogTag, exception.getMessage(), exception);
        } else {
            reportExceptionToDatabase(exception);
        }
    }

    /**
     * Check if the type of exception is in the list of exception types to be ignored.
     *
     * @param exception
     * @return
     */
    private static boolean shouldExceptionBeIgnored(Throwable exception) {
        final Class<?>[] ignoreList = {
                ConnectException.class,
                UnknownHostException.class,
                ConnectTimeoutException.class,
                SocketException.class,
                SocketTimeoutException.class
        };
        for (Class<?> clazz: ignoreList) {
            if (clazz.equals(exception.getClass())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Invoke the async task to submit the exception infomation to backend server.
     * @param e
     */
    private static void reportExceptionToDatabase(Throwable e) {

        CommonUtils.printDevLog(null == e ? "e is null" : e.getMessage());

        PickOutTask.getInstance().collectData(e);
    }

}
