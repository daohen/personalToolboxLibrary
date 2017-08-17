package com.daohen.personal.toolbox.library.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.WindowManager;


/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:21
 */
public class Contexts {

    private static Context context;

    private Contexts(){}

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

    public static int getColor(int colorRes){
        checkNull();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorRes);
        }
        return context.getResources().getColor(colorRes, null);
    }

    public static String getString(int stringRes){
        checkNull();
        return context.getString(stringRes);
    }

    public static Drawable getDrawable(int drawableRes){
        checkNull();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(drawableRes);
        }
        return context.getResources().getDrawable(drawableRes, null);
    }

    private static void checkNull(){
        if (context == null)
            throw new NullPointerException("请在Application里面先调用Contexts.setContext()");
    }

}
