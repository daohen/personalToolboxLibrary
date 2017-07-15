package com.daohen.personal.toolbox.library.util;

import android.util.Log;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/15 23:58
 */
public class Logs {

    private static final String TAG = "daohen";

    public static void e(String msg){
        if (Booleans.isRelease())
            return;

        Log.e(TAG, msg);
    }

    public static void d(String msg){
        if (Booleans.isRelease())
            return;

        Log.d(TAG, msg);
    }

    public static void i(String msg){
        if (Booleans.isRelease())
            return;

        Log.i(TAG, msg);
    }

    public static void v(String msg){
        if (Booleans.isRelease())
            return;

        Log.v(TAG, msg);
    }

    public static void w(String msg){
        if (Booleans.isRelease())
            return;

        Log.w(TAG, msg);
    }

}
