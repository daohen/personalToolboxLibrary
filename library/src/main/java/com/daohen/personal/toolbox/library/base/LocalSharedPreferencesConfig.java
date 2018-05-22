package com.daohen.personal.toolbox.library.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.daohen.personal.toolbox.library.util.Contexts;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/09/05 10:55
 */

public class LocalSharedPreferencesConfig {

    private SharedPreferences sp;

    public LocalSharedPreferencesConfig(String name){
        transferSharedPreferences(name);
    }

    public void transferSharedPreferences(String name){
        sp = Contexts.get().getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value){
        sp.edit().putString(key, value).apply();
    }

    public String getString(String key, String dValue){
        return sp.getString(key, dValue);
    }

    public String getString(String key){
        return getString(key, null);
    }

    public void saveBoolean(String key, boolean value){
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean dValue){
        return sp.getBoolean(key, dValue);
    }

    public boolean getBoolean(String key){
        return getBoolean(key, false);
    }

    public void saveInteger(String key, int value){
        sp.edit().putInt(key, value).apply();
    }

    public int getInteger(String key, int dValue){
        return sp.getInt(key, dValue);
    }

    public int getInteger(String key){
        return getInteger(key, 0);
    }

    public void saveLong(String key, long value){
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long dvalue){
        return sp.getLong(key, dvalue);
    }

    public long getLong(String key){
        return getLong(key, 0L);
    }

    public void setFloat(String key, float value){
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float dvalue){
        return sp.getFloat(key, dvalue);
    }

    public float getFloat(String key){
        return getFloat(key, 0f);
    }

    public void clear(){
        sp.edit().clear().apply();
    }

    public void remove(String key){
        sp.edit().remove(key).apply();
    }

    public SharedPreferences getSp(){
        return sp;
    }
}
