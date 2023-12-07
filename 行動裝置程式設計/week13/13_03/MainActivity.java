package com.coderyo.d20231207;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String DBname = "先進公司.db";
	private static final int DBversion = 1;
	private EditText etCusNo;
	private Button btOk; 
	private CompDBHper dbHper; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();  //user define
	}

	private void buildViews() {
		etCusNo = (EditText)findViewById(R.id.etIdCusNo);
		btOk = (Button)findViewById(R.id.btIdOk);
		btOk.setOnClickListener(btOkListener);         	

		initDB(); 
	}

	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, 
					null, DBversion);
		dbHper.createTB();	
	}
	@Override
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, 
					null, DBversion); 
	}
	@Override
	public void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close(); 
			dbHper = null;
		}
	}
	
	private OnClickListener btOkListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			String result=null;
			String CusNo = etCusNo.getText().toString().trim();
			if(CusNo.length()!=0){
				String rec = dbHper.FindRec(CusNo);
				if(rec!=null){
					result="找到的客戶資料為 ：\n"+rec;						
				}else{
					result="找不到指定的 客戶編號："+CusNo;						
				}
				Toast.makeText(MainActivity.this, 
						result, Toast.LENGTH_LONG).show();
			} 
		}
	};
}
