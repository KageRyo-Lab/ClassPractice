package com.coderyo.D20231207;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	private Button alphaButton = null;
	private ImageView image = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		alphaButton = (Button) findViewById(R.id.alphaButton);
		image = (ImageView) findViewById(R.id.image);
		alphaButton.setOnClickListener(new AlphaButtonListener());
	}
	class AlphaButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 創建一個AnimationSet物件，參數為Boolean型，
			// true表示使用Animation的interpolator，false則是使用自己的
			AnimationSet animationSet = new AnimationSet(true);
			// 創建一個AlphaAnimation物件，參數從完全的透明度，到完全的不透明
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			// 設置動畫執行的時間
			alphaAnimation.setDuration(500);
			// 將alphaAnimation對象添加到AnimationSet當中
			animationSet.addAnimation(alphaAnimation);
			// 使用ImageView的startAnimation方法執行動畫
			image.startAnimation(animationSet);
		}
	}
}
