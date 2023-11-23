package com.coderyo.d20231123_2;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class MainActivity extends AppCompatActivity {

    private EditText edtID, edtPW, edtContent;
    private Button btnAppend, btnClear, btnEnd;
    private static final String FILENAME="login.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();  //user define
    }

    private void buildViews(){
        edtID = (EditText)findViewById(R.id.edtID);
        edtPW = (EditText)findViewById(R.id.edtPW);

        btnAppend = (Button)findViewById(R.id.btnAppend);

        btnAppend.setOnClickListener(btnAppendlistener);


    }




    private OnClickListener btnAppendlistener = new OnClickListener() {
        public void onClick(View v) {
            //檢查帳號及密碼是否都有輸入
            if(edtID.getText().toString().equals("") || edtPW.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "帳號及密碼都必須輸入！", Toast.LENGTH_LONG) .show();

            }
            FileOutputStream fout=null; //建立寫入資料流
            BufferedOutputStream buffout=null;
            try {
                fout=openFileOutput(FILENAME, MODE_APPEND);
                buffout=new BufferedOutputStream(fout);
                //寫入帳號及密碼
                buffout.write(edtID.getText().toString().getBytes());
                buffout.write("\n".getBytes());
                buffout.write(edtPW.getText().toString().getBytes());
                buffout.write("\n".getBytes());
                buffout.close();
            }	catch(Exception e)	{
                e.printStackTrace();
            }
            edtID.setText("");
            edtPW.setText("");

        }
    };



}
