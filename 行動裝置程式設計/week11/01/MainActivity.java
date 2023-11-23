package com.coderyo.d20231123_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText username; // 用戶名
    private EditText passwd; // 密碼
    private EditText mail; // 郵件
    private Button save; // 保存
    private Button load; // 讀取

    private SharedPreferences sharedPreferences;
    private Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText) findViewById(R.id.username);
        passwd = (EditText) findViewById(R.id.passwd);
        mail = (EditText) findViewById(R.id.mail);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);

        // 從Context中獲取SharePreference，並設置為私有模式
        sharedPreferences = getSharedPreferences("usersetting", MODE_PRIVATE);
        // 獲得Editor用於資料的寫入
        editor = sharedPreferences.edit();

        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String userString = username.getText().toString();
                String passwdString = passwd.getText().toString();
                String mailString = mail.getText().toString();

                // 往Editor裡面存入資料
                editor.putString("username", userString);
                editor.putString("passwd", passwdString);
                editor.putString("mail", mailString);
                // 遞交至SharePreference文件
                editor.commit();

                Toast.makeText(MainActivity.this,
                        "資料保存完畢", Toast.LENGTH_SHORT).show();
            }
        });
        load.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 從SharePreference中獲取資料
                String userString = sharedPreferences.getString("username", null);
                String passwdString = sharedPreferences.getString("passwd", null);
                String mailString = sharedPreferences.getString("mail", null);

                username.setText(userString);
                passwd.setText(passwdString);
                mail.setText(mailString);

                Toast.makeText(MainActivity.this,
                        "讀取數據完畢", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
