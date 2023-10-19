package com.coderyo.d20231019_2;

import android.app.Activity;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ImageViewTest extends Activity
{
	// ���xһ���L���DƬ�����
	int[] images = new int[]{
		R.drawable.lijiang,
		R.drawable.qiao,
		R.drawable.shuangta,
		R.drawable.shui,
		R.drawable.xiangbi,
	};
	// ���x�A�O�@ʾ�ĈDƬ
	int currentImg = 2;
	// ���x�DƬ�ĳ�ʼ͸����
	private int alpha = 255;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button plus = (Button) findViewById(R.id.plus);
		final Button minus = (Button) findViewById(R.id.minus);
		final ImageView image1 = (ImageView) findViewById(R.id.image1);
		final Button next = (Button) findViewById(R.id.next);
		// ���x�鿴��һ���DƬ�ıO ��
		next.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ����ImageView�@ʾ��һ���DƬ
				image1.setImageResource(
					images[++currentImg % images.length]);
			}
		});
		// ���x��׃�DƬ͸���ȵķ���
		OnClickListener listener = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (v == plus)
				{
					alpha += 20;
				}
				if (v == minus)
				{
					alpha -= 20;
				}
				if (alpha >= 255)
				{
					alpha = 255;
				}
				if (alpha <= 0)
				{
					alpha = 0;
				}
				// ��׃�DƬ��͸����
				image1.setAlpha(alpha);
			}
		};
		// ��ɂ����o��ӱO ��
		plus.setOnClickListener(listener);
		minus.setOnClickListener(listener);
		image1.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				BitmapDrawable bitmapDrawable = (BitmapDrawable) image1
						.getDrawable();
				// �@ȡ��һ���DƬ�@ʾ���е��cꇈD
				Bitmap bitmap = bitmapDrawable.getBitmap();
				// bitmap�DƬ���H��С�c��һ��ImageView�Ŀs�ű���
				double scale = bitmap.getWidth() / 320.0;
				// �@ȡ��Ҫ�@ʾ�ĈDƬ���_ʼ�c
				int x = (int) (event.getX() * scale);
				int y = (int) (event.getY() * scale);
				if (x + 120 > bitmap.getWidth())
				{
					x = bitmap.getWidth() - 120;
				}
				if (y + 120 > bitmap.getHeight())
				{
					y = bitmap.getHeight() - 120;
				}
	
				return false;
			}
		});
	}
}
