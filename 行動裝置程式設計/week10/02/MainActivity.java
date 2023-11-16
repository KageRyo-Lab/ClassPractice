package com.coderyo.d20231116;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private  Button bu1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //設置佈局檔
        bu1=(Button)findViewById(R.id.button1);
        //為button按鈕做監聽
        bu1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(intent2); //啟動Intent進行隱式跳轉
            }
        });
    }
}
