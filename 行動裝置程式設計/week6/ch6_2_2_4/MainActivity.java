package com.coderyo.d20231019_3;

import android.os.Bundle;
import android.app.Activity;
import android.widget.QuickContactBadge;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    QuickContactBadge badge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        badge = (QuickContactBadge) findViewById(R.id.badge);
    }
}