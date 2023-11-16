package com.coderyo.d20231116;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Second extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        // 取得介面元件
        Button btnHome=(Button)findViewById(R.id.btnHome);
        // 設定 button 的 Listener
        btnHome.setOnClickListener(btnHomeListener);
    }

    private Button.OnClickListener btnHomeListener = new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };
}