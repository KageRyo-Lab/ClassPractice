package com.coderyo.d20230928_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnDo;
    private TextView txtShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDo = (Button)findViewById(R.id.button);
        txtShow = (TextView)findViewById(R.id.textView);
        btnDo.setOnClickListener(btnDoListener);
    }

    int tmp = 0; // 判斷按鈕是否已經被按下用的
    private Button.OnClickListener btnDoListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (tmp == 0){
                txtShow.setText("原神啟動!（再按一下可以回去）");
                tmp = 1;
            }else{
                txtShow.setText("請按我");
                tmp = 0;
            }
        }
    };
}
