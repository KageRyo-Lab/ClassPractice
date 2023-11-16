package com.coderyo.d20231116;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bn = (Button) findViewById(R.id.bn);
        // 為bn按鈕添加一個監聽器
        bn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 創建Intent
                Intent intent = new Intent();
                String data = "http://kageryo.coderyo.com";
                // 根據指定字串解析出Uri物件
                Uri uri = Uri.parse(data);
                // 為Intent設置Action屬性
                intent.setAction(Intent.ACTION_VIEW);
                // 設置Data屬性
                intent.setData(uri);
                startActivity(intent);
            }
        });


        Button call = (Button) findViewById(R.id.call);
        // 為edit按鈕添加一個監聽器
        call.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 創建Intent
                Intent intent = new Intent();
                // 為Intent設置Action屬性（動作為：撥號）
                intent.setAction(Intent.ACTION_DIAL);
                String data = "tel:0981404168";
                // 根據指定字串解析出Uri物件
                Uri uri = Uri.parse(data);
                // 設置Data屬性
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
