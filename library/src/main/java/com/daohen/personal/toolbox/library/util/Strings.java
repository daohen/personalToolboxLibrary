package com.daohen.personal.toolbox.library.util;

import android.text.TextUtils;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 11:33
 */
public class Strings {

    public static boolean isNull(String str){
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }

}
