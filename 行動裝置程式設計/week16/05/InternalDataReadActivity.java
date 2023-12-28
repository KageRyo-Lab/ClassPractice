package com.coderyo.d20231228;


import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InternalDataReadActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // �{�ø����
        setContentView(R.layout.result);// ʹ�ÁѾ֙n
      
	  FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = openFileInput("login");// �@�Ùnݔ����
            buffer = new byte[fis.available()];// ���x�����Y�ϵ����
            fis.read(buffer);// ��ݔ�������xȡ�Y��
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();// �P�]�nݔ����
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        String data = new String(buffer);// �@������б�����Y��
        String username = data.split(" ")[0];// �@��username
        String password = data.split(" ")[1];// �@��password
        usernameTV.setText("�Ñ�����" + username);// �@ʾ�Ñ���
        passwordTV.setText("��    �a��" + password);// �@ʾ�ܴa
    }
}
