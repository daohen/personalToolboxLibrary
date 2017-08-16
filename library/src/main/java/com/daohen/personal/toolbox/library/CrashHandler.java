package com.daohen.personal.toolbox.library;

import android.content.Intent;
import android.os.Environment;

import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Logs;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/17 00:15
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static CrashHandler get(){
        return gDefault.get();
    }

    public void setErrorIntent(Intent intent){
        this.errorIntent = intent;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logs.e("12233444");

        if (!handleException(e) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处
            Logs.e("yyyyyyyy");
            mDefaultHandler.uncaughtException(t, e);
        } else {
            // 跳转到崩溃提示Activity
            Logs.e("nnnnnnn");
            errorIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Contexts.getContext().startActivity(errorIntent);
            System.exit(0);// 关闭已奔溃的app进程
        }

    }

    private Intent errorIntent;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private CrashHandler(){
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 收集错误信息
//        getCrashInfo(ex);

        return true;
    }

    private void getCrashInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String errorMessage = writer.toString();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            String mFilePath = Environment.getExternalStorageDirectory() + "/" + App.ERROR_FILENAME;
//            FileTxt.WirteTxt(mFilePath, FileTxt.ReadTxt(mFilePath) + '\n' + errorMessage);
        } else {
//            Log.i(App.TAG, "哦豁，说好的SD呢...");
        }

    }

    private static final Singleton<CrashHandler> gDefault = new Singleton<CrashHandler>() {
        @Override
        protected CrashHandler create() {
            return new CrashHandler();
        }
    };
}
