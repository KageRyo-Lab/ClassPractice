package com.coderyo.d20231012_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private CheckBox chkBasketBall,chkFootBall,chkBaseBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件的 id
        txtResult=(TextView) findViewById(R.id.txtResult);
        chkBasketBall = (CheckBox) findViewById(R.id.chkBasketBall);
        chkFootBall = (CheckBox) findViewById(R.id.chkFootBall);
        chkBaseBall = (CheckBox) findViewById(R.id.chkBaseBall);

        // 設定 CheckBox 元件 CheckedChange 事件的 listener 為 myListener
        chkBasketBall.setOnCheckedChangeListener(myListener);
        chkFootBall.setOnCheckedChangeListener(myListener);
        chkBaseBall.setOnCheckedChangeListener(myListener);
    }

    //  定義  onCheckedChanged 方法
    private CheckBox.OnCheckedChangeListener myListener=new CheckBox.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int n=0;        // 記錄共選了多少項
            String sel="";  // 所有的選項結果
            String s1="",s2="",s3="";  // 單一選項的結果
            if(chkBasketBall.isChecked()){
                n++;
                s1=chkBasketBall.getText().toString() + " ";
            }else{
                s1="";
            }
            if(chkFootBall.isChecked()){
                n++;
                s2=chkFootBall.getText().toString() + " ";
            }else{
                s2="";
            }
            if(chkBaseBall.isChecked()){
                n++;
                s3=chkBaseBall.getText().toString() + " ";
            }else{
                s3="";
            }
            sel=s1+s2+s3;
            txtResult.setText("最喜歡的球類有： " + sel + "\n共 " + n + " 項");
        }
    };
}
