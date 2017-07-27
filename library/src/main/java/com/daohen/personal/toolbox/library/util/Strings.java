package com.daohen.personal.toolbox.library.util;

import android.text.TextUtils;
import android.util.Base64;

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

    public static String base64Encode(String str){
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    public static String base64Decode(String str){
        return new String(Base64.decode(str,Base64.DEFAULT));
    }

}
