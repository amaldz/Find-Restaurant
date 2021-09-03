package com.dz.restaurant.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dz.restaurant.R;
import com.dz.restaurant.helpers.SqlHelper;


public class NoInternetActivity extends AppCompatActivity {
    public static SqlHelper sqlHelper;
    Button btnretry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_no_internet);
            btnretry = (Button) findViewById(R.id.btn_Retry);
            btnretry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sqlHelper.checkConnection(getApplicationContext())) {
                        sqlHelper.executeUrl(sqlHelper.isShowLoading());
                        finish();
                    }
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while(true){
                        if(System.currentTimeMillis() - time >= 5000){
                            if(sqlHelper.checkConnection(getApplicationContext())){
                                sqlHelper.executeUrl(sqlHelper.isShowLoading());
                                break;
                            }else{
                                time = System.currentTimeMillis();
                            }
                        }
                    }
                    finish();
                }
            }).start();
        }catch (Exception e){
        }
    }
}
