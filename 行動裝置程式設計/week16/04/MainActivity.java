package com.coderyo.d20231228;



import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		XmlResourceParser xrp=getResources().getXml(R.xml.customers);	//獲取XML文檔
		StringBuilder sb=new StringBuilder("");	//創建一個空的字串構建器
		try {
			//如果沒有到XML文檔的結尾處
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType()==XmlResourceParser.START_TAG){	//判斷是否為開始標記
					String tagName=xrp.getName();	//獲取標記名
					if(tagName.equals("customer")){	//如果標記名是customer
						sb.append("姓名："+xrp.getAttributeValue(0)+"   ");		//獲取客戶姓名
						sb.append("電話："+xrp.getAttributeValue(1)+"   ");	//獲取聯繫電話
						sb.append("E-mail："+xrp.getAttributeValue(2));	//獲取E-mail
						sb.append("\n");	//添加分行符號
					}
				}
				xrp.next();	//下一個標記
			}
			TextView tv=(TextView)findViewById(R.id.show);	//獲取顯示文字方塊
			tv.setText(sb.toString());	//將獲取到XML檔的內容顯示到文字方塊中
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
