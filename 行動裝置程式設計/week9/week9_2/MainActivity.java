package com.coderyo.week9_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private CheckBox chkBuger1,chkBuger2,chkBuger3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件的 id
        txtResult=(TextView) findViewById(R.id.txtResult);
        chkBuger1 = (CheckBox) findViewById(R.id.chkBuger1);
        chkBuger2 = (CheckBox) findViewById(R.id.chkBuger2);
        chkBuger3 = (CheckBox) findViewById(R.id.chkBuger3);

        // 設定 CheckBox 元件 CheckedChange 事件的 listener 為 myListener
        chkBuger1.setOnCheckedChangeListener(myListener);
        chkBuger2.setOnCheckedChangeListener(myListener);
        chkBuger3.setOnCheckedChangeListener(myListener);
    }

    //  定義  onCheckedChanged 方法
    private CheckBox.OnCheckedChangeListener myListener=new CheckBox.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int n=0;        // 記錄共選了多少項
            String sel="";  // 所有的選項結果
            String s1="",s2="",s3="";  // 單一選項的結果
            if(chkBuger1.isChecked()){
                n++;
                s1=chkBuger1.getText().toString() + " ";
            }else{
                s1="";
            }
            if(chkBuger2.isChecked()){
                n++;
                s2=chkBuger2.getText().toString() + " ";
            }else{
                s2="";
            }
            if(chkBuger3.isChecked()){
                n++;
                s3=chkBuger3.getText().toString() + " ";
            }else{
                s3="";
            }
            sel=s1+s2+s3;
            txtResult.setText("您點的餐點有： " + sel + "共 " + n + " 項");
        }
    };
}
