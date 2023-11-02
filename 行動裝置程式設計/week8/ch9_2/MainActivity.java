package com.coderyo.d20231102;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linear;
    private SeekBar sbColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
    }
    private void findViews() {
        linear = (LinearLayout)findViewById(R.id.linear);
        sbColor = (SeekBar)findViewById(R.id.sbColor);
        sbColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                linear.setBackgroundColor(
                        Color.rgb(progress, progress, progress));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String msg = getString(R.string.color_value) + seekBar.getProgress();
                Toast.makeText(
                                MainActivity.this,
                                msg,
                                Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}