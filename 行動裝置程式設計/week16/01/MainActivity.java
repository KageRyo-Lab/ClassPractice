package com.coderyo.d20231228;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
                SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);// �@��˽����͵�SharedPreferences
                Editor editor = sp.edit();// �@��Editor����
                editor.putString("username", username);// �����Ñ���
                editor.putString("password", password);// �����ܴa
                editor.commit();// �_�J�ύ
                Intent intent = new Intent();// ����Intent����
                intent.setClass(MainActivity.this, SharedPreferencesReadActivity.class);// ָ�����D��SharedPreferencesReadActivity
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
