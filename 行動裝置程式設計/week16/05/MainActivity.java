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
        final EditText usernameET = (EditText) findViewById(R.id.username);// �@���Ñ��������
        final EditText passwordET = (EditText) findViewById(R.id.password);// �@���ܴa�����
        Button login = (Button) findViewById(R.id.login);// �@�ð��o�����
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();// �@���Ñ���
                String password = passwordET.getText().toString();// �@���ܴa
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("login", MODE_PRIVATE);// �@�Ùn��ݔ����
                    fos.write((username + " " + password).getBytes());// �����Ñ������ܴa
                    fos.flush();// �������
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();// �P�]�n��ݔ����
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Intent intent = new Intent();// ����Intent���
                intent.setClass(MainActivity.this, InternalDataReadActivity.class);// ָ�����D��InternalDataReadActivity
                startActivity(intent);// ���F���D
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
