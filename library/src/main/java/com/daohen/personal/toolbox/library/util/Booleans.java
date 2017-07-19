package com.daohen.personal.toolbox.library.util;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/15 23:54
 */
public class Booleans {

    private Booleans(){}

    private static boolean release;
    public static void setRelease(boolean isRelease){
        Booleans.release = isRelease;
    }

    public static boolean isRelease(){
        return release;
    }

}
