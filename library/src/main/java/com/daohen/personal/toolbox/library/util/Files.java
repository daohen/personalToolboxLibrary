package com.daohen.personal.toolbox.library.util;

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
        return Contexts.getContext().getCacheDir().getPath();
    }

    /**
     * /data/data/<application package>/files
     * @return
     */
    public static String getFilesPath(){
        return Contexts.getContext().getFilesDir().getPath();
    }

}
