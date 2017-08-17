package com.daohen.personaltoolboxlibrary;

import android.app.Application;
import android.content.Intent;

import com.daohen.personal.toolbox.library.CrashHandler;
import com.daohen.personal.toolbox.library.util.Booleans;
import com.daohen.personal.toolbox.library.util.Contexts;

/**
 * CREATE BY DAOHEN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/17 00:21
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contexts.setContext(this);
        CrashHandler.get().init(this, new Intent(this, ErrorActivity.class));
    }

}
