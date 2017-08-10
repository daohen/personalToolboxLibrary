package com.daohen.personal.toolbox.library.util;

import android.support.annotation.StringDef;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/10 15:38
 */

public class Dates {

    public static final String FORMAT_1 = "yyyyMMddhhmmss";
    public static final String FORMAT_2 = "yyyy/MM/dd/ hh:mm:ss";
    public static final String FORMAT_3 = "yyyy-MM-dd hh:mm:ss";

    @StringDef({FORMAT_1, FORMAT_2, FORMAT_3})
    public @interface DateFormat{}

    public static String getString(Date date, @DateFormat String format){
        return getChinaSimpleDateFormat(format).format(date);
    }

    public static String getCurrentString(@DateFormat String format){
        return getString(new Date(), format);
    }

    private static SimpleDateFormat getChinaSimpleDateFormat(@DateFormat String format){
        return new SimpleDateFormat(format, Locale.CHINA);
    }
}
