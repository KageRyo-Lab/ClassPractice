package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ViewFlipper;
public class MainActivity extends AppCompatActivity {

    private String[] mStrings = {"向上推", "向左推", "交叉淡出", "超空間"};
    private ViewFlipper vwFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vwFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
        vwFlipper.startFlipping();

        Spinner animSpin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mStrings);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        animSpin.setAdapter(adapter);
        animSpin.setOnItemSelectedListener(spnEduListener);
    }

    private OnItemSelectedListener spnEduListener =
            new OnItemSelectedListener () {
                public void onItemSelected(AdapterView<?> parent,
                                           View v, int position, long id) {
                    switch (position) {
                        case 0:
                            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,R.anim.push_up_in));
                            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,R.anim.push_up_out));
                            break;
                        case 1:
                            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,R.anim.push_left_in));
                            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,R.anim.push_left_out));
                            break;
                        case 2:
                            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,android.R.anim.fade_in));
                            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,android.R.anim.fade_out));
                            break;
                        default:
                            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,R.anim.hyperspace_in));
                            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                                    MainActivity.this,R.anim.hyperspace_out));
                            break;
                    }
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };
}
