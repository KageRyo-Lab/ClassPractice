package com.coderyo.d20231228;


import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InternalDataReadActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 調用父類方法
        setContentView(R.layout.result);// 使用佈局檔
      
	  FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = openFileInput("login");// 獲得檔輸入流
            buffer = new byte[fis.available()];// 定義保存資料的陣列
            fis.read(buffer);// 從輸入流中讀取資料
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();// 關閉檔輸入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        String data = new String(buffer);// 獲得陣列中保存的資料
        String username = data.split(" ")[0];// 獲得username
        String password = data.split(" ")[1];// 獲得password
        usernameTV.setText("用戶名：" + username);// 顯示用戶名
        passwordTV.setText("密    碼：" + password);// 顯示密碼
    }
}
