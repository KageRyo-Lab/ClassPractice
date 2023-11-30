package com.coderyo.d20231123_2;
import android.app.Activity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.PrintStream;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "crazyit.bin";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        System.out.println(new StringBuilder("a").append("b").append("c")
                .toString());
        // 獲取兩個按鈕
        Button read = (Button) findViewById(R.id.read);
        Button write = (Button) findViewById(R.id.write);
        // 獲取兩個文字方塊
        final EditText edit1 = (EditText) findViewById(R.id.edit1);
        final EditText edit2 = (EditText) findViewById(R.id.edit2);
        // 為write按鈕綁定事件監聽器
        write.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                // 將edit1中的內容寫入檔中
                write(edit1.getText().toString());
                edit1.setText("");
            }
        });

        read.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 讀取指定檔中的內容，並顯示出來
                edit2.setText(read());
            }
        });
    }

    private String read()
    {
        try
        {
            // 打開檔輸入流
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            // 讀取檔內容
            while ((hasRead = fis.read(buff)) > 0)
            {
                sb.append(new String(buff, 0, hasRead));
            }
            // 關閉檔輸入流
            fis.close();
            return sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String content)
    {
        try
        {
            // 以追加模式打開檔輸出流
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
            // 將FileOutputStream包裝成PrintStream
            PrintStream ps = new PrintStream(fos);
            // 輸出檔內容
            ps.println(content);
            // 關閉檔輸出流
            ps.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
