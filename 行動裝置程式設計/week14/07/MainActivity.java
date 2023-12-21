package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private static final String[] INTERPOLATORS = {
            "加速", "減速", "加速/減速","搶先", "過衝", "搶先/過衝","彈跳"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner animSpin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, INTERPOLATORS);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        animSpin.setAdapter(adapter);
        animSpin.setOnItemSelectedListener(spnListener);
    }

    private OnItemSelectedListener spnListener =
            new OnItemSelectedListener () {
                public void onItemSelected(AdapterView<?> parent,
                                           View v, int position, long id) {
                    final View target = findViewById(R.id.target);
                    final View targetParent = (View) target.getParent();

                    Animation anim = new TranslateAnimation(0.0f,
                            targetParent.getWidth() - target.getWidth() -
                                    targetParent.getPaddingLeft() -
                                    targetParent.getPaddingRight(), 0.0f, 0.0f);
                    anim.setDuration(1000);
                    anim.setStartOffset(300);
                    anim.setRepeatMode(Animation.RESTART);
                    anim.setRepeatCount(Animation.INFINITE);

                    switch (position) {
                        case 0:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this, +android.R.anim.accelerate_interpolator));
                            break;
                        case 1:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this, +android.R.anim.decelerate_interpolator));
                            break;
                        case 2:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this,
                                    +android.R.anim.accelerate_decelerate_interpolator));
                            break;
                        case 3:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this, +android.R.anim.anticipate_interpolator));
                            break;
                        case 4:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this, +android.R.anim.overshoot_interpolator));
                            break;
                        case 5:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this,
                                    +android.R.anim.anticipate_overshoot_interpolator));
                            break;
                        case 6:
                            anim.setInterpolator(AnimationUtils.loadInterpolator(
                                    MainActivity.this, +android.R.anim.bounce_interpolator));
                            break;
                    }
                    target.startAnimation(anim);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };
}
