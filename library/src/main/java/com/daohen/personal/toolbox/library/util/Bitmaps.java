package com.daohen.personal.toolbox.library.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 13:47
 */
public class Bitmaps {

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
