package com.coderyo.d20231012_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 獲取 RadioGroup 的實例
        RadioGroup radGroupBalls = findViewById(R.id.radGroupBalls);

        // 設置 RadioGroup 的點擊事件監聽器
        radGroupBalls.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 獲取選中的 RadioButton
                RadioButton radButton = findViewById(checkedId);

                // 獲取選中的球類運動
                String ball = radButton.getText().toString();

                // 將結果顯示在文本框中
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setText("您的最喜歡的球類運動是：" + ball);
            }
        });
    }
}