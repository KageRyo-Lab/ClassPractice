package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {

    private ImageView ivPhoto1;
    private Animation anim;
    private Button btOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();  //user define
    }

    private void buildViews(){
        btOk = (Button)findViewById(R.id.btIdOk);
        btOk.setOnClickListener(btListener);
    }
    private OnClickListener btListener =
            new OnClickListener() {
                public void onClick(View v) {
                    ivPhoto1 = (ImageView)findViewById(R.id.ivIdPhoto1);
                    //定義Tween Animation動畫的xml檔案
                    anim=AnimationUtils.loadAnimation(
                            MainActivity.this, R.anim.anim_acts);
                    ivPhoto1.setAnimation(anim);
                    ivPhoto1.startAnimation(anim);
                }
            };
}