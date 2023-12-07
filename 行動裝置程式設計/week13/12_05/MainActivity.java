package com.coderyo.D20231207;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	private Button scaleButton = null;
	private ImageView image = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		scaleButton = (Button) findViewById(R.id.scaleButton);
		image = (ImageView) findViewById(R.id.image);
		scaleButton.setOnClickListener(new ScaleButtonListener());
	}
	class ScaleButtonListener implements OnClickListener {
		public void onClick(View v) {
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(0, 0.1f, 0,
					0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			scaleAnimation.setDuration(1000);
			animationSet.addAnimation(scaleAnimation);
			image.startAnimation(animationSet);
		}
	}
}
