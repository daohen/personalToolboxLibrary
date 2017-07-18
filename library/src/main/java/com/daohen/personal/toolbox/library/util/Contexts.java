package com.daohen.personal.toolbox.library.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:21
 */
public class Contexts {

    private static Context context;

    public static void setContext(Context context){
        Contexts.context = context;
    }

    public static Context getContext(){
        checkNull();

        return context;
    }

    public static int getScreenWidth(){
        checkNull();

        return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(){
        checkNull();

        return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }

    private static void checkNull(){
        if (context == null)
            throw new NullPointerException("需要先调用setContext");
    }
}
