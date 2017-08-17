package com.daohen.personal.toolbox.library;

import android.app.Application;
import android.content.Intent;

import com.daohen.personal.toolbox.library.util.Booleans;
import com.daohen.personal.toolbox.library.util.Files;
import com.daohen.personal.toolbox.library.util.Strings;

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

    public void init(Application application, Intent errorIntent){
        this.application = application;
        this.errorIntent = errorIntent;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!Booleans.isRelease() || !handleException(e) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处
            mDefaultHandler.uncaughtException(t, e);
        } else {
            // 跳转到崩溃提示Activity
            errorIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            application.startActivity(errorIntent);
            System.exit(0);// 关闭已崩溃的app进程
        }
    }

    private Application application;
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
        getCrashInfo(ex);
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
        String errPath = Files.getCrashPath() + "/crash_" + Strings.generateFileNameFromDate("txt");
        Files.writeStringToFile(errPath, errorMessage);
    }

    private static final Singleton<CrashHandler> gDefault = new Singleton<CrashHandler>() {
        @Override
        protected CrashHandler create() {
            return new CrashHandler();
        }
    };
}
