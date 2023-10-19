package com.coderyo.d20231019;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int[] imgId={R.drawable.img01,R.drawable.img02,R.drawable.img03,
            R.drawable.img04,R.drawable.img05,R.drawable.img06};
    private Button btnPrev,btnNext;
    private ImageView imgPhoto;
    int p=0;
    int count=imgId.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
    }

    // 上一張
    private Button.OnClickListener btnPrevListener=new Button.OnClickListener(){
        public void onClick(View v){
            p--;
            if (p<0)
                p=count-1;
            imgPhoto.setImageResource(imgId[p]);
            setTitle("第" + (p+1) + "/" + count);
        }
    };

    // 下一張
    private Button.OnClickListener btnNextListener=new Button.OnClickListener(){
        public void onClick(View v){
            p++;
            if (p==count)
                p=0;
            imgPhoto.setImageResource(imgId[p]);
            setTitle("第" + (p+1) + "/" + count);
        }
    };
}