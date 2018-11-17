package com.hannibal.scalpel.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.hannibal.scalpel.BuildConfig;
import com.hannibal.scalpel.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CommonUtils {


    /**
     * 打印开发日志
     * @param format
     * @param objects
     */
    public static void printDevLog(String format, Object... objects) {
        if (BuildConfig.DEBUG && !TextUtils.isEmpty(format)) {
            if (objects != null && objects.length > 0)
                Log.e(Constant.DevLogTag, String.format(format, objects));
            else
                Log.e(Constant.DevLogTag, format);
        }
    }

    /**
     * Check if the user is using the 2G mobile network connection.
     *
     * @param context
     * @return
     */
    public static boolean isUsing2GNetworkConnection(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionManager.getActiveNetworkInfo();
        final int[] _2GNetworkTypes = {
                TelephonyManager.NETWORK_TYPE_CDMA,
                TelephonyManager.NETWORK_TYPE_EDGE,
                TelephonyManager.NETWORK_TYPE_GPRS,
                TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_IDEN
        };

        if (netInfo != null) {
            int networkSubType = netInfo.getSubtype();
            for (int networkType: _2GNetworkTypes) {
                if (networkType == networkSubType) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前时间 格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = getDateFormat(true);
        return df.format(new Date());
    }

    public static String getCurrentTime(boolean utc) {
        SimpleDateFormat df = getDateFormat(utc);
        return df.format(new Date());
    }

    private static SimpleDateFormat getDateFormat(boolean utc) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        if (utc) {
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return df;
    }

    /**
     * 字符串判空
     * @param str
     * @return
     */
    public static String checkNull(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

}
