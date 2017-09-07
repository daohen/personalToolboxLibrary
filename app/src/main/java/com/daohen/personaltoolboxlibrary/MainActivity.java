package com.daohen.personaltoolboxlibrary;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.daohen.personal.toolbox.library.util.Files;
import com.daohen.personal.toolbox.library.util.Logs;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.con).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1==1){
                    throw new NullPointerException("that is null");
                }
            }
        });


        Logs.e(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
    }
}
