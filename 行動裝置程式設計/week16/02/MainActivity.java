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
     //定義需要實用的控制項
	private EditText mEtTemp1,mEtTemp2,mEtHumi,mEtLight,mEtCo;
	private Button mBtnSave,mBtnClear,mBtnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化控制項
    }
	private void initView() {
		//通過findViewById找到控制項強制轉換成相應類型並賦值
		mEtTemp1 = (EditText)findViewById(R.id.etTemp1);
		mEtTemp2 = (EditText)findViewById(R.id.etTemp2);
		mEtHumi = (EditText)findViewById(R.id.etHumi);
		mEtLight = (EditText)findViewById(R.id.etLight);
		mEtCo = (EditText)findViewById(R.id.etCo);

		mBtnSave = (Button)findViewById(R.id.btnSave);
		mBtnClear = (Button)findViewById(R.id.btnClear);
		mBtnRead = (Button)findViewById(R.id.btnRead);
		//設置按鈕按一下事件
		//保存按鈕
		mBtnSave.setOnClickListener(new OnClickListener() {
			 @SuppressLint({ "NewApi", "CommitPrefEdits" }) @Override
			public void onClick(View v) {
				//獲取EditText的值，如果為空則return
				String temp1 = mEtTemp1.getText().toString();
				String temp2 = mEtTemp2.getText().toString();
				String humi = mEtHumi.getText().toString();
				String light= mEtLight.getText().toString();
				String co = mEtCo.getText().toString();
				if(temp1.isEmpty()||temp2.isEmpty()||humi.isEmpty()||light.isEmpty()||co.isEmpty())return;
				
				//產生實體一個SharedPreferences物件，定一名字和訪問類型，這裡我們儲存的檔案名是zhcs.xml，訪問類型是私有的，只允許本程式
				SharedPreferences mSharedPreferences = getSharedPreferences("zhcs", Context.MODE_PRIVATE);
				//獲取編輯器
				Editor mEditor = mSharedPreferences.edit();
				mEditor.putString("最低溫度",temp1);
				mEditor.putString("最高溫度",temp2);
				mEditor.putString("濕度臨界值",humi);
				mEditor.putString("光照臨界值",light);
				mEditor.putString("一氧化碳臨界值",co);
				mEditor.commit();//提交修改
				Toast.makeText(MainActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
			}
		});
		//清空按鈕
		mBtnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//清空編輯方塊
				mEtTemp1.setText("");
				mEtTemp2.setText("");
				mEtHumi.setText("");
				mEtLight.setText("");
				mEtCo.setText("");
				Toast.makeText(MainActivity.this, "清空成功！", Toast.LENGTH_SHORT).show();
			}
		});
		//讀取按鈕
		mBtnRead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				SharedPreferences mSharedPreferences = getSharedPreferences("zhcs", Context.MODE_PRIVATE);
				//getString()第二個參數為缺省值，如果preference中不存在該key，將返回缺省值
				String temp1 = mSharedPreferences.getString("最低溫度","");
				String temp2 = mSharedPreferences.getString("最高溫度","");
				String humi = mSharedPreferences.getString("濕度臨界值","");
				String light = mSharedPreferences.getString("光照臨界值","");
				String co = mSharedPreferences.getString("一氧化碳臨界值","");
				//設置編輯方塊值
				mEtTemp1.setText(temp1);
				mEtTemp2.setText(temp2);
				mEtHumi.setText(humi);
				mEtLight.setText(light);
				mEtCo.setText(co);

				Toast.makeText(MainActivity.this, "讀取成功！", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
