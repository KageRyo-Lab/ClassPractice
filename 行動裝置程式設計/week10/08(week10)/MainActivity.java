package com.coderyo.d20231123_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 定義一個Action常量
    final static String CRAZYIT_ACTION =
            "org.crazyit.intent.action.CRAZYIT_ACTION";
    // 定義一個Category常量
    final static String CRAZYIT_CATEGORY =
            "org.crazyit.intent.category.CRAZYIT_CATEGORY";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                Intent intent = new Intent();
                // 設置Action屬性
                intent.setAction(MainActivity.CRAZYIT_ACTION);
                // 添加Category屬性
                intent.addCategory(MainActivity.CRAZYIT_CATEGORY);
                startActivity(intent);
            }
        });
    }
}