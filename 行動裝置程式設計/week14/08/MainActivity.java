package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Random;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private int i = 0; // 記錄其打到了幾隻地鼠
    private ImageView mouse; // 聲明一個ImageView物件
    private Handler handler; // 聲明一個Handler物件
    public int[][] position = new int[][] { { 95, 134 }, { 369, 117 },
            { 231, 99 }, { 240, 119 }, { 323, 93 }, { 350, 151 },
            { 181, 146 } }; // 創建一個表示地鼠位置的陣列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mouse = (ImageView) findViewById(R.id.imageView1); // 獲取ImageView對象
        mouse.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE); // 設置地鼠不顯示
                i++;
                Toast.makeText(MainActivity.this, "打到[ " + i + " ]只地鼠！",
                        Toast.LENGTH_SHORT).show(); // 顯示消息提示框
                return false;
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if (msg.what == 0x101) {
                    index = msg.arg1; // 獲取位置索引值
                    mouse.setX(position[index][0]); // 設置X軸位置
                    mouse.setY(position[index][1]); // 設置Y軸位置
                    mouse.setVisibility(View.VISIBLE); // 設置地鼠顯示
                }
                super.handleMessage(msg);
            }

        };
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                int index = 0; // 創建一個記錄地鼠位置的索引值
                while (!Thread.currentThread().isInterrupted()) {
                    index = new Random().nextInt(position.length); // 產生一個亂數
                    Message m = handler.obtainMessage(); // 獲取一個Message
                    m.what = 0x101; // 設置消息標識
                    m.arg1 = index; // 保存地滑鼠位置的索引值
                    handler.sendMessage(m); // 發送消息

                    try {
                        Thread.sleep(new Random().nextInt(500) + 500); // 休眠一段時間
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        });
        t.start(); // 開啟執行緒

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
