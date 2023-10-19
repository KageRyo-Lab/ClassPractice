package com.coderyo.d20231019_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "您按一下了普通按鈕", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    public void myClick(View view) {
        Toast toast = Toast.makeText(MainActivity.this, "您按一下了圖片按鈕", Toast.LENGTH_SHORT);
        toast.show();
    }
}

