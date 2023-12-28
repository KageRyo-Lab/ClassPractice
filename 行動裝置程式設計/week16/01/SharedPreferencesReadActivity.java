package com.coderyo.d20231228;
import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.content.SharedPreferences;

public class SharedPreferencesReadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 調用父類方法
        setContentView(R.layout.result);// 設置佈局檔
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);// 獲得私有類型的SharedPreferences
        String username = sp.getString("username", "mr");// 獲得用戶名
        String password = sp.getString("password", "001");// 獲得密碼
        usernameTV.setText("用戶名：" + username);// 顯示用戶名
        passwordTV.setText("密    碼：" + password);// 顯示密碼
    }

}
