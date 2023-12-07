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
			// ����һ��AnimationSet�����������Boolean�ͣ�
			// true��ʾʹ��Animation��interpolator��false�t��ʹ���Լ���
			AnimationSet animationSet = new AnimationSet(true);
			// ����һ��AlphaAnimation�������������ȫ��͸���ȣ�����ȫ�Ĳ�͸��
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			// �O�ÄӮ����еĕr�g
			alphaAnimation.setDuration(500);
			// ��alphaAnimation������ӵ�AnimationSet����
			animationSet.addAnimation(alphaAnimation);
			// ʹ��ImageView��startAnimation�������ЄӮ�
			image.startAnimation(animationSet);
		}
	}
}
