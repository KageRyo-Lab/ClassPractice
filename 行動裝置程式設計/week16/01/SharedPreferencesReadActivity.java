package com.coderyo.d20231228;
import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.content.SharedPreferences;

public class SharedPreferencesReadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // �{�ø����
        setContentView(R.layout.result);// �O�ÁѾ֙n
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);// �@��˽����͵�SharedPreferences
        String username = sp.getString("username", "mr");// �@���Ñ���
        String password = sp.getString("password", "001");// �@���ܴa
        usernameTV.setText("�Ñ�����" + username);// �@ʾ�Ñ���
        passwordTV.setText("��    �a��" + password);// �@ʾ�ܴa
    }

}
