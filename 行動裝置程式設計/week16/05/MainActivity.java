package com.coderyo.d20231228;


import android.app.Activity;
import android.os.Bundle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        final EditText usernameET = (EditText) findViewById(R.id.username);// 獲得用戶名控制項
        final EditText passwordET = (EditText) findViewById(R.id.password);// 獲得密碼控制項
        Button login = (Button) findViewById(R.id.login);// 獲得按鈕控制項
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();// 獲得用戶名
                String password = passwordET.getText().toString();// 獲得密碼
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("login", MODE_PRIVATE);// 獲得檔案輸出流
                    fos.write((username + " " + password).getBytes());// 保存用戶名和密碼
                    fos.flush();// 清除緩存
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();// 關閉檔案輸出流
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Intent intent = new Intent();// 創建Intent物件
                intent.setClass(MainActivity.this, InternalDataReadActivity.class);// 指定跳轉到InternalDataReadActivity
                startActivity(intent);// 實現跳轉
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
