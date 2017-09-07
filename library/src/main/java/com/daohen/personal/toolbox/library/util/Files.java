package com.daohen.personal.toolbox.library.util;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by alun on 17/7/18.
 */

public class Files {

    private Files(){}

    /**
     * /data/data/<application package>/cache
     * @return
     */
    public static String getCachePath(){
        return Contexts.get().getContext().getCacheDir().getPath();
    }

    /**
     * /data/data/<application package>/files
     * @return
     */
    public static String getFilesPath(){
        return Contexts.get().getContext().getFilesDir().getPath();
    }

    /**
     * /storage/emulated/0/Android/data/<application package>/cache
     * @return
     */
    public static String getExternalCachePath(){
        return Contexts.get().getContext().getExternalCacheDir().getPath();
    }

    /**
     * type = null /storage/emulated/0/Android/data/<application package>/files
     * type = Environment.DIRECTORY_DCIM……
     * @param type
     * @return
     */
    public static String getExternalFilesPath(String type){
        return Contexts.get().getContext().getExternalFilesDir(type).getPath();
    }

    /**
     * /storage/emulated/0
     * @return
     */
    public static String getStorageSdcardPath(){
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String getCrashPath(){
        return getFilesPath() + "/crash";
    }

    public static String getPathFromUri(Uri uri){
        String path;
        if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
            path = uri.getPath();
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
            path = getPath(Contexts.get().getContext(), uri);
        } else {//4.4以下下系统调用方法
            path = getRealPathFromURI(Contexts.get().getContext(), uri);
        }
        return path;
    }

    public static boolean writeStringToFile(String filePath, String content){
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            osw.append(content);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null){
                    osw.flush();
                    osw.close();
                }
                if (fos != null){
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static String getRealPathFromURI(Context context, Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if(null != cursor&&cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {// DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {// MediaStore (and general)
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @Deprecated
    public static boolean bitmapToFile(Bitmap bitmap, String outFile, int quality, Bitmap.CompressFormat format){
        File file = new File(outFile);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        return bitmapToFile(bitmap, file, quality, format);
    }

    public static boolean bitmapToFile(Bitmap bitmap, File outFile, int quality, Bitmap.CompressFormat format) {
        if (bitmap == null)return false;
        boolean ret = false;
        FileOutputStream out = null;
        try {
            if (!outFile.getParentFile().exists())
                outFile.getParentFile().mkdirs();

            out = new FileOutputStream(outFile);
            ret = bitmap.compress(format, quality, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }




}
