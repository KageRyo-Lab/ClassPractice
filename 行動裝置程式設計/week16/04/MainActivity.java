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

		XmlResourceParser xrp=getResources().getXml(R.xml.customers);	//�@ȡXML�ęn
		StringBuilder sb=new StringBuilder("");	//����һ���յ��ִ�������
		try {
			//����]�е�XML�ęn�ĽYβ̎
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType()==XmlResourceParser.START_TAG){	//�Д��Ƿ���_ʼ��ӛ
					String tagName=xrp.getName();	//�@ȡ��ӛ��
					if(tagName.equals("customer")){	//�����ӛ����customer
						sb.append("������"+xrp.getAttributeValue(0)+"   ");		//�@ȡ�͑�����
						sb.append("�Ԓ��"+xrp.getAttributeValue(1)+"   ");	//�@ȡ�M�Ԓ
						sb.append("E-mail��"+xrp.getAttributeValue(2));	//�@ȡE-mail
						sb.append("\n");	//��ӷ��з�̖
					}
				}
				xrp.next();	//��һ����ӛ
			}
			TextView tv=(TextView)findViewById(R.id.show);	//�@ȡ�@ʾ���ַ��K
			tv.setText(sb.toString());	//���@ȡ��XML�n�ă����@ʾ�����ַ��K��
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
