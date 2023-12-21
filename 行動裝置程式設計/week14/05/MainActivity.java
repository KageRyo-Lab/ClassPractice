package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private Button btlogin;
    private Animation shakeAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btlogin = (Button)findViewById(R.id.btIdlogin);
        btlogin.setOnClickListener(btListener);
    }

    private OnClickListener btListener = new OnClickListener() {
        public void onClick(View v) {
            shakeAnim=AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.shake);
            findViewById(R.id.etIdAccNo).startAnimation(shakeAnim);
        }
    };
}