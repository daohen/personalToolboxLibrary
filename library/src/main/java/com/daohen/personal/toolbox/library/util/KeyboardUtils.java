package com.daohen.personal.toolbox.library.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/09/21 18:31
 */

public class KeyboardUtils {

    private static InputMethodManager getInputMethodManager(Context context){
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static void hideSoftInput(Activity activity){
        View view = activity.getCurrentFocus();
        if (view == null){
            view = new View(activity);
        }
        getInputMethodManager(activity).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        getInputMethodManager(context).showSoftInput(edit, 0);
    }

    public static void toggleSoftInput(Context context) {
        getInputMethodManager(context).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
