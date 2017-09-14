package com.daohen.personal.toolbox.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/09/14 18:29
 */

public class BaseActivity extends AppCompatActivity {

    private static final String BR_ACTION_FINISH = "BR_ACTION_FINISH";

    private List<BroadcastReceiver> broadcastReceiverList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public <T extends View> T findView(@IdRes int id){
        return (T) findViewById(id);
    }

    public void setFullScreen(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void registerReceiver(BroadcastReceiver receiver, String action){
        broadcastReceiverList.add(receiver);
        registerReceiver(receiver, new IntentFilter(action));
    }

    public void registerFinishReceiver(){
        BroadcastReceiver finishReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };
        broadcastReceiverList.add(finishReceiver);
        registerReceiver(finishReceiver, new IntentFilter(BR_ACTION_FINISH));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (BroadcastReceiver receiver : broadcastReceiverList){
            unregisterReceiver(receiver);
        }
    }
}
