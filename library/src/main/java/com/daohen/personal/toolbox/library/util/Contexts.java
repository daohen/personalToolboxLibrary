package com.daohen.personal.toolbox.library.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;

import com.daohen.personal.toolbox.library.Singleton;


/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:21
 */
public class Contexts {

    public static Contexts get(){
        return gDefault.get();
    }

    private Context context;
    private DisplayMetrics displayMetrics;
    public void setContext(Context context){
        this.context = context;
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    public Context getContext(){
        return context;
    }

    public int getScreenWidth(){
        checkNull();
        return displayMetrics.widthPixels;
    }

    public int getScreenHeight(){
        checkNull();
        return displayMetrics.heightPixels;
    }

    public int getColor(int colorRes){
        checkNull();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorRes);
        }
        return context.getResources().getColor(colorRes, null);
    }

    public String getString(int stringRes){
        checkNull();
        return context.getString(stringRes);
    }

    public Drawable getDrawable(int drawableRes){
        checkNull();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(drawableRes);
        }
        return context.getResources().getDrawable(drawableRes, null);
    }

    public Bitmap getBitmapForResourceName(String name){
        checkNull();
        int rid = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return BitmapFactory.decodeResource(context.getResources(), rid);
    }

    public int dip2px(float dipValue) {
        return (int) (dipValue * displayMetrics.density + 0.5f);
    }

    public int px2dip(float pxValue) {
        return (int) (pxValue / displayMetrics.density + 0.5f);
    }

    public int sp2px(float spValue) {
        return (int) (spValue * displayMetrics.scaledDensity + 0.5f);
    }

    private static final Singleton<Contexts> gDefault = new Singleton<Contexts>() {
        @Override
        protected Contexts create() {
            return new Contexts();
        }
    };

    private void checkNull(){
        if (context == null)
            throw new NullPointerException("请在Application里面先调用Contexts.get().setContext()");
    }
}
