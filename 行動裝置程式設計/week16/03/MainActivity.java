package com.coderyo.d20231228;


import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Spinner mSpinner1;
	private Button mBtnInsert;
	//private ImageView mImgSearch;
	private static final String[] mSpData ={"����","������"};
	//�O��Ҫ�����ֵ
	private String id;//="201508260528";
	private String name;//="����";
	private String phone;//="13666666666";
	private String address;//="XXʡXX��XX�hXX�ֵ�";
	private String money;//="168";
	private int state;
	
	private TextView id_tv;
	private TextView name_tv;
	private TextView phone_tv;
	private TextView address_tv;
	private TextView money_tv;
	private MyDb db;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
	private void initView() {
		id_tv=(TextView)findViewById(R.id.textView1);
		name_tv=(TextView)findViewById(R.id.textView2);
		phone_tv=(TextView)findViewById(R.id.textView3);
		address_tv=(TextView)findViewById(R.id.textView4);
		money_tv=(TextView)findViewById(R.id.textView5);
		
		id=id_tv.getText().toString().trim();
		name=name_tv.getText().toString().trim();
		phone=phone_tv.getText().toString().trim();
		address=address_tv.getText().toString().trim();
		money=money_tv.getText().toString().trim();
		
		
        //��ʼ��spinner�����
		mSpinner1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this , android.R.layout. simple_spinner_item , mSpData );
		mSpinner1.setAdapter(adapter);
		
		mBtnInsert = (Button)findViewById(R.id.btnInsert);
		mBtnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��0λ��δ��؛�tλ0����֮��1
				state = mSpinner1.getSelectedItemPosition();
				for(int i=0;i<8;i++){
				   MyDb.getInstance(getApplicationContext()).insert(id+i, name, phone, address, money,state);
				}
				Toast.makeText(MainActivity.this, "����ɹ���", Toast.LENGTH_SHORT).show();
				MyDb.getInstance(getApplicationContext()).close();
				Toast.makeText(MainActivity.this, "�P�]�ɹ���", Toast.LENGTH_SHORT).show();
			}
		});
		
		ImageView mImgSearch = (ImageView)findViewById(R.id.imgSearch);
		mImgSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});
	}

}
