package com.coderyo.week9_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    // 宣告全域變數
    private EditText edtHeight, edtWeight;
    private TextView txtResult;
    private Button btnCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件
        edtHeight=(EditText)findViewById(R.id.edtHeight); // 身高
        edtWeight=(EditText)findViewById(R.id.edtWeight); // 體重
        btnCount=(Button)findViewById(R.id.btnCount);     // 計算按鈕
        txtResult=(TextView)findViewById(R.id.txtResult); // 顯示結果

        //為Button元件加入Click事件的偵聽，觸發時執行自訂方法 btnTranListener
        btnCount.setOnClickListener(btnTranListener);
    }

    private Button.OnClickListener btnTranListener=new Button.OnClickListener(){
        public void onClick(View v){
            double height=Double.parseDouble(edtHeight.getText().toString());
            double weight=Double.parseDouble(edtWeight.getText().toString());
            double bmi=weight/((height/100)*(height/100));
            txtResult.setText("BMI = "+bmi);
        }
    };
}
