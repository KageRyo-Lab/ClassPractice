package com.coderyo.d20230928_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Chronometer ch;
    Button start;
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ch = (Chronometer) findViewById(R.id.test);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        stop.setEnabled(false);

        //啟動
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                // 設置開始計時時間
                ch.setBase(SystemClock.elapsedRealtime());
                // 啟動計時器
                ch.start();
                start.setEnabled(false);
                stop.setEnabled(true);
                start.setText("原神啟動");
            }
        });

        // 停止
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 重置計時器
                ch.setBase(SystemClock.elapsedRealtime());
                // 停止計時器
                ch.stop();
                start.setEnabled(true);
                start.setText("啟動");
            }
        });

        // 計時器
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
        {
            @Override
            public void onChronometerTick(Chronometer ch)
            {
                // 如果從開始計時到現在超過了20s。
                if (SystemClock.elapsedRealtime() - ch.getBase() > 20 * 1000)
                {
                    ch.stop();
                    start.setEnabled(true);
                }
            }
        });
    }
}