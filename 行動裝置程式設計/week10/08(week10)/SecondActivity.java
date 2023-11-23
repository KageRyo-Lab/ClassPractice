package com.coderyo.d20231123_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import java.util.Set;

public class SecondActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		EditText show = (EditText) findViewById(R.id.show);
		// 獲取該Activity對應的Intent的Action屬性
		String action = getIntent().getAction();
		// 顯示Action屬性
		show.setText("Action為：" + action);
		EditText cate = (EditText) findViewById(R.id.cate);
		// 獲取該Activity對應的Intent的Category屬性
		Set<String> cates = getIntent().getCategories();
		// 顯示Action屬性
		cate.setText("Category屬性為：" + cates);
	}
}
