package com.daohen.personal.toolbox.library.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 11:33
 */
public class Strings {

    private Strings(){}

    public static final char SLASH = '-';
    public static final String PROTOCOL_HTTP = "http://";
    public static final String PROTOCOL_HTTPS = "https://";

    public static final String SUFFIX_JPG = ".jpg";
    public static final String SUFFIX_JPEG = ".jpeg";
    public static final String SUFFIX_PNG = ".png";

    public static boolean isNull(String str){
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }

    public static boolean isUrl(String str){
        return !isNull(str) && (str.startsWith(PROTOCOL_HTTP) || str.startsWith(PROTOCOL_HTTPS));
    }

    public static boolean isPhoneNumber(String str){
        return !isNull(str) && str.length() == 11;
    }

    public static String base64Encode(@NonNull String str){
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    public static String base64Decode(@NonNull String str){
        return new String(Base64.decode(str,Base64.DEFAULT));
    }

    public static String generateFileNameFromDate(@NonNull String suffix){
        StringBuilder sb = new StringBuilder();
        sb.append(Dates.getCurrentString(Dates.FORMAT_1));
        if (suffix.startsWith("."))
            sb.append(suffix);
        else
            sb.append(".").append(suffix);

        return sb.toString();
    }
}
