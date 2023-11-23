package com.coderyo.d20231123;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
	final int CODE= 0x717;							//定義一個請求碼常量
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button submit=(Button)findViewById(R.id.submit);	//獲取提交按鈕
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String user=((EditText)findViewById(R.id.user)).getText().toString();	//獲取輸入的用戶
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();	//獲取輸入的密碼
				String repwd=((EditText)findViewById(R.id.repwd)).getText().toString();	//獲取輸入的確認密碼
				String email=((EditText)findViewById(R.id.email)).getText().toString();	//獲取輸入的E-mail位址
				if(!"".equals(user) && !"".equals(pwd) && !"".equals(email)){

					if(!pwd.equals(repwd)){	//判斷兩次輸入的密碼是否一致
						Toast.makeText(MainActivity.this, "兩次輸入的密碼不一致，請重新輸入！", Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.pwd)).setText("");	//清空密碼編輯方塊
						((EditText)findViewById(R.id.repwd)).setText("");	//清空確認密碼編輯方塊
						((EditText)findViewById(R.id.pwd)).requestFocus();	//讓密碼編輯方塊獲得焦點
					}else{	//將輸入的資訊保存到Bundle中，並啟動一個新的Activity顯示輸入的使用者註冊資訊
						Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
						Bundle bundle=new Bundle();	//創建並產生實體一個Bundle物件
						bundle.putCharSequence("user", user);	//保存用戶名
						bundle.putCharSequence("pwd", pwd);	//保存密碼
						bundle.putCharSequence("email", email);	//保存E-mail位址
						intent.putExtras(bundle);	//將Bundle物件添加到Intent物件中
						startActivityForResult(intent, CODE);					//啟動新的Activity

					}
				}else{
					Toast.makeText(MainActivity.this, "請將註冊資訊輸入完整！", Toast.LENGTH_LONG).show();
				}

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
