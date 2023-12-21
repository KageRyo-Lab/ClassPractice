package com.coderyo.d20231214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
public class MainActivity extends AppCompatActivity {

    private Button btBeg;
    private ListView list1,list2;
    private ListView visibleList,invisibleList;
    private ObjectAnimator visToInvis,invisToVis;
    private Interpolator accelerator,decelerator;
    private static final String[] LIST_1 = new String[] {
            "一","二","三","四","五","六"};
    private static final String[] LIST_2 = new String[] {
            "一箭雙鵰","二人同心","三羊開泰","四海昇平","五福臨門","六六大順"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();  //user define
    }

    private void buildViews(){
        list1 = (ListView) findViewById(R.id.list_1);
        list2 = (ListView) findViewById(R.id.list_2);

        final ArrayAdapter<String> adap1 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, LIST_1);
        final ArrayAdapter<String> adap2 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, LIST_2);

        list1.setAdapter(adap1);
        list2.setAdapter(adap2);
        list2.setRotationY(-90f);

        btBeg = (Button) findViewById(R.id.btIdBeg);
        btBeg.setOnClickListener(btListener);
    }

    private OnClickListener btListener =
            new OnClickListener() {
                public void onClick(View v) {
                    flipit();
                }
            };

    private void flipit() {
        accelerator = new AccelerateInterpolator();
        decelerator = new DecelerateInterpolator();
        if (list1.getVisibility() == View.GONE) {
            visibleList = list2;
            invisibleList = list1;
        } else {
            invisibleList = list2;
            visibleList = list1;
        }
        visToInvis = ObjectAnimator.ofFloat(
                visibleList, "rotationY", 0f, 90f);
        visToInvis.setDuration(500);
        visToInvis.setInterpolator(accelerator);
        invisToVis = ObjectAnimator.ofFloat(
                invisibleList, "rotationY",-90f, 0f);
        invisToVis.setDuration(500);
        invisToVis.setInterpolator(decelerator);
        visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {
                visibleList.setVisibility(View.GONE);
                invisToVis.start();
                invisibleList.setVisibility(View.VISIBLE);
            }
        });
        visToInvis.start();
    }
}
