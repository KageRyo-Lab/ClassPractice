package com.coderyo.d20231123;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);	//設置該Activity中要顯示的內容視圖
		final Intent intent=getIntent();	//獲取Intent對象
		Bundle bundle=intent.getExtras();	//獲取傳遞的資料包
		TextView user=(TextView)findViewById(R.id.user);		//獲取顯示用戶名的TextView元件
		user.setText("用戶名："+bundle.getString("user"));		//獲取輸入的用戶名並顯示到TextView元件中
		TextView pwd=(TextView)findViewById(R.id.pwd);		//獲取顯示密碼的TextView元件
		pwd.setText("密碼："+bundle.getString("pwd"));		//獲取輸入的密碼並顯示到TextView元件中
		TextView email=(TextView)findViewById(R.id.email);		//獲取顯示E-mail的TextView元件
		email.setText("E-mail："+bundle.getString("email"));		//獲取輸入的E-mail並顯示到TextView元件中
	}

}
