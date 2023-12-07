package com.coderyo.d20231130;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
    private Button rotateButton = null;
    private ImageView image = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotateButton = (Button) findViewById(R.id.rotateButton);
        image = (ImageView) findViewById(R.id.image);
        rotateButton.setOnClickListener(new RotateButtonListener());
    }
    class RotateButtonListener implements OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            // 參數1：從哪個旋轉角度開始
            // 參數2：轉到什麼角度
            // 後4個參數用於設置圍繞著旋轉的圓的圓心在哪裡
            // 參數3：確定x軸座標的類型，有ABSOLUT絕對座標、RELATIVE_TO_SELF相對於自身座標、RELATIVE_TO_PARENT相對于父控制項的座標
            // 參數4：x軸的值，0.5f表明是以自身這個控制項的一半長度為x軸
            // 參數5：確定y軸座標的類型
            // 參數6：y軸的值，0.5f表明是以自身這個控制項的一半長度為x軸
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);
            animationSet.addAnimation(rotateAnimation);
            image.startAnimation(animationSet);
        }
    }
}
