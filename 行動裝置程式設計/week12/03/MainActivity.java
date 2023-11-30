package com.coderyo.d20231130;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
    private Button translateButton = null;
    private ImageView image = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translateButton = (Button) findViewById(R.id.translateButton);
        image = (ImageView) findViewById(R.id.image);
        translateButton.setOnClickListener(new TranslateButtonListener());
    }
    class TranslateButtonListener implements OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            // 參數1～2：x軸的開始位置
            // 參數3～4：y軸的開始位置
            // 參數5～6：x軸的結束位置
            // 參數7～8：x軸的結束位置
            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            translateAnimation.setDuration(1000);
            animationSet.addAnimation(translateAnimation);
            image.startAnimation(animationSet);
        }
    }
}
