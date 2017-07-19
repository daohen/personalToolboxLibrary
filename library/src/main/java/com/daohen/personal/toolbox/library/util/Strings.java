package com.daohen.personal.toolbox.library.util;

import android.text.TextUtils;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 11:33
 */
public class Strings {

    private Strings(){}

    public static final String PROTOCOL_HTTP = "http://";
    public static final String PROTOCOL_HTTPS = "https://";

    public static boolean isNull(String str){
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }

    public static boolean isUrl(String str){
        return !isNull(str) && (str.startsWith(PROTOCOL_HTTP) || str.startsWith(PROTOCOL_HTTPS));
    }

}
