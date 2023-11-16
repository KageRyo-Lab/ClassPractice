package com.coderyo.d20231116;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void overrideType(View source)
    {
        Intent intent = new Intent();
        // 先為Intent設置Type屬性
        intent.setType("abc/xyz");
        // 再為Intent設置Data屬性，覆蓋Type屬性
        intent.setData(Uri.parse("lee://www.google.com"));
        Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
    }

    public void overrideData(View source)
    {
        Intent intent = new Intent();
        // 先為Intent設置Data屬性
        intent.setData(Uri.parse("lee://www.yahoo.com"));
        // 再為Intent設置Type屬性，覆蓋Data屬性
        intent.setType("abc/xyz");
        Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
    }

    public void dataAndType(View source)
    {
        Intent intent = new Intent();
        // 同時設置Intent的Data、Type屬性
        intent.setDataAndType(Uri.parse("lee://www.cnn.com"),
                "abc/xyz");
        Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
    }
}
