package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
public class MainActivity extends AppCompatActivity {

    private AnimationDrawable anim;
    private Button btBeg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();  //user define
    }

    private void buildViews(){
        ImageView ivFlow =
                (ImageView) findViewById(R.id.ivIdFlow);
//      ivFlow.setBackgroundResource(+R.anim.animact);
//      anim = (AnimationDrawable) ivFlow.getBackground();
        ivFlow.setImageResource(+R.anim.animact);
        anim = (AnimationDrawable) ivFlow.getDrawable();
        anim.start();

        btBeg = (Button)findViewById(R.id.btIdBeg);
        btBeg.setOnClickListener(btListener);
    }

    private OnClickListener btListener =
            new OnClickListener() {
                public void onClick(View v) {
                    if ( ! anim.isRunning() ) {
                        anim.start();
                        btBeg.setText("按此鈕結束");
                    }
                    else {
                        anim.stop();
                        btBeg.setText("按此鈕開始");
                    }
                }
            };
}