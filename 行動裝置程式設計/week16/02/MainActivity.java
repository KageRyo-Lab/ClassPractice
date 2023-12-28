package com.coderyo.d20231228;

import android.os.Bundle;
import android.app.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
     //���x��Ҫ���õĿ����
	private EditText mEtTemp1,mEtTemp2,mEtHumi,mEtLight,mEtCo;
	private Button mBtnSave,mBtnClear,mBtnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//��ʼ�������
    }
	private void initView() {
		//ͨ�^findViewById�ҵ�����헏����D�Q��������́K�xֵ
		mEtTemp1 = (EditText)findViewById(R.id.etTemp1);
		mEtTemp2 = (EditText)findViewById(R.id.etTemp2);
		mEtHumi = (EditText)findViewById(R.id.etHumi);
		mEtLight = (EditText)findViewById(R.id.etLight);
		mEtCo = (EditText)findViewById(R.id.etCo);

		mBtnSave = (Button)findViewById(R.id.btnSave);
		mBtnClear = (Button)findViewById(R.id.btnClear);
		mBtnRead = (Button)findViewById(R.id.btnRead);
		//�O�ð��o��һ���¼�
		//���水�o
		mBtnSave.setOnClickListener(new OnClickListener() {
			 @SuppressLint({ "NewApi", "CommitPrefEdits" }) @Override
			public void onClick(View v) {
				//�@ȡEditText��ֵ�������Մtreturn
				String temp1 = mEtTemp1.getText().toString();
				String temp2 = mEtTemp2.getText().toString();
				String humi = mEtHumi.getText().toString();
				String light= mEtLight.getText().toString();
				String co = mEtCo.getText().toString();
				if(temp1.isEmpty()||temp2.isEmpty()||humi.isEmpty()||light.isEmpty()||co.isEmpty())return;
				
				//�a�����wһ��SharedPreferences�������һ���ֺ��L����ͣ��@�e�҂�����ęn������zhcs.xml���L�������˽�еģ�ֻ���S����ʽ
				SharedPreferences mSharedPreferences = getSharedPreferences("zhcs", Context.MODE_PRIVATE);
				//�@ȡ��݋��
				Editor mEditor = mSharedPreferences.edit();
				mEditor.putString("��͜ض�",temp1);
				mEditor.putString("��ߜض�",temp2);
				mEditor.putString("����R��ֵ",humi);
				mEditor.putString("�����R��ֵ",light);
				mEditor.putString("һ����̼�R��ֵ",co);
				mEditor.commit();//�ύ�޸�
				Toast.makeText(MainActivity.this, "����ɹ���", Toast.LENGTH_SHORT).show();
			}
		});
		//��հ��o
		mBtnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��վ�݋���K
				mEtTemp1.setText("");
				mEtTemp2.setText("");
				mEtHumi.setText("");
				mEtLight.setText("");
				mEtCo.setText("");
				Toast.makeText(MainActivity.this, "��ճɹ���", Toast.LENGTH_SHORT).show();
			}
		});
		//�xȡ���o
		mBtnRead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				SharedPreferences mSharedPreferences = getSharedPreferences("zhcs", Context.MODE_PRIVATE);
				//getString()�ڶ���������ȱʡֵ�����preference�в�����ԓkey��������ȱʡֵ
				String temp1 = mSharedPreferences.getString("��͜ض�","");
				String temp2 = mSharedPreferences.getString("��ߜض�","");
				String humi = mSharedPreferences.getString("����R��ֵ","");
				String light = mSharedPreferences.getString("�����R��ֵ","");
				String co = mSharedPreferences.getString("һ����̼�R��ֵ","");
				//�O�þ�݋���Kֵ
				mEtTemp1.setText(temp1);
				mEtTemp2.setText(temp2);
				mEtHumi.setText(humi);
				mEtLight.setText(light);
				mEtCo.setText(co);

				Toast.makeText(MainActivity.this, "�xȡ�ɹ���", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
