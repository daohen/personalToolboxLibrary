package com.daohen.personal.toolbox.library.util;

import android.widget.Toast;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:17
 */
public class Toasts {

    private Toasts(){}

    public static void showLong(String msg){
        Toast.makeText(Contexts.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void show(int rId, int duration){
        Toast.makeText(Contexts.getContext(), rId, duration).show();
    }

    public static void showShort(String msg){
        Toast.makeText(Contexts.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
