package com.daohen.personaltoolboxlibrary;

import android.app.Application;
import android.content.Intent;

import com.daohen.personal.toolbox.library.CrashHandler;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/17 00:21
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.get().setErrorIntent(new Intent(this, ErrorActivity.class));
    }
}
