package com.coderyo.d20240104;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import java.util.List;

enum Action {
    SLEEP, WALK, FLY
}
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final static int RIGHT = 1;
    private final static int LEFT  = -1;

    private SensorManager sensorManager;            // 感應器的實作部份

    private int screenW;

    private ImageView 		character01 ;
    private ImageView 		back1, back2 ,back3 ;
    private RelativeLayout  relative1;
    // 虎圖向右
    private int [] resIdwc = {R.drawable.wc0000 , R.drawable.wc0001,R.drawable.wc0002,R.drawable.wc0003,
            R.drawable.wc0004,R.drawable.wc0005,R.drawable.wc0006,R.drawable.wc0007	};
    // 鷹圖向右
    private int [] resIdrc = {R.drawable.rc0000 , R.drawable.rc0001,R.drawable.rc0002,R.drawable.rc0003,
            R.drawable.rc0004,R.drawable.rc0005,R.drawable.rc0006,R.drawable.rc0007	};
    // 虎圖向右蹲臥: R.drawable.ce0000

    private int dx=0 ,speed=1 ,pspeed=1, dir=LEFT;

    private Action character = Action.SLEEP;

    // 圖片
    private Bitmap bitmapSleep;
    private Bitmap [] bitmapTiger = new Bitmap[resIdwc.length];
    private Bitmap [] bitmapEagle = new Bitmap[resIdrc.length];

    private int pcount = 0, touched = 0 ;
    private boolean bPaused;

    Handler handler = new Handler();
    Thread r = new Thread(){

        public void run() {
            pcount++;
            // 控制角色的分解動作播放
            switch(character) {
                case SLEEP:
                    character01.setImageBitmap(bitmapSleep);
                    break;
                case WALK:
                    character01.setImageBitmap(bitmapTiger[pcount%resIdwc.length]);
                    break;
                case FLY:
                    character01.setImageBitmap(bitmapEagle[pcount%resIdrc.length]);
                    break;
            }
            // 控制角色的左右方向
            switch(dir) {
                case LEFT:
                    character01.setScaleX(-1);
                    break;
                case RIGHT:
                    character01.setScaleX(1);
                    break;
            }
            handler.postDelayed(r, 100/pspeed);
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        relative1=(RelativeLayout) findViewById(R.id.relativelayout1);
        Toast.makeText(this, "點擊畫面控制  站立移動/趴下休息", Toast.LENGTH_LONG).show();

        relative1.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                touched++;
                if(touched%2==1){
                    screenW = relative1.getWidth();
                    Toast.makeText(getBaseContext(), "翻轉手機控制  緩步/快走/飛行", Toast.LENGTH_LONG).show();
                    moveImage();
                }
                else{
                    return;
                }
            }});

        findViewsAndPrepareImages();
        // 3
        handler.postDelayed(r, 0);
    }

    private void findViewsAndPrepareImages() {
        back1 = (ImageView) findViewById(R.id.imageView1);
        back2 = (ImageView) findViewById(R.id.imageView2);
        back3 = (ImageView) findViewById(R.id.imageView3);
        character01 = (ImageView) findViewById(R.id.imageView4);
        //
        bitmapSleep = BitmapFactory.decodeResource(getResources(), R.drawable.ce0000);
        for(int i=0; i<bitmapTiger.length; i++) {
            bitmapTiger[i] = BitmapFactory.decodeResource(getResources(), resIdwc[i]);
            bitmapEagle[i] = BitmapFactory.decodeResource(getResources(), resIdrc[i]);
        }
    }

    private void moveImage(){

        if(bPaused) return;

        // 動態位置調整
        back1.setX(             -dx);
        back2.setX( this.screenW-dx);
        back3.setX(-this.screenW-dx);
        System.out.println("dx = " + dx);	// 向左走：dx<0 , 向右走：dx>0

        handler.postDelayed(new Runnable(){

            @Override
            public void run() {

                if( dx>screenW || dx<-screenW ){
                    dx=0;
                }
                else{
                    dx += (speed*dir);
                }

                if( touched % 2 != 0){
                    moveImage();
                }
                else{
                    Toast.makeText(getBaseContext(), "點擊畫面控制  站立移動/趴下休息", Toast.LENGTH_SHORT).show();
                    return;
                }

            }}, 10/speed);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bPaused = false;
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sensorManager.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        bPaused = true;
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;

        float xDir = values[0];

        if(touched%2==0){
            character = Action.SLEEP;
            speed = 1;
            pspeed = 1;
        }
        else if(xDir < -5) {
            character = Action.FLY;
            speed = 16;
            pspeed = 1;
            dir = RIGHT;
        }
        else if(xDir < -2.5) {
            character = Action.WALK;
            speed = 8;
            pspeed = 3;
            dir = RIGHT;
        }
        else if(xDir < 0) {
            character = Action.WALK;
            speed = 2;
            pspeed = 1;
            dir = RIGHT;
        }
        else if(xDir > 5.0) {
            character = Action.FLY;
            speed = 16;
            pspeed = 1;
            dir = LEFT;
        }
        else if(xDir > 2.5) {
            character = Action.WALK;
            speed = 8;
            pspeed = 3;
            dir = LEFT;
        }
        else  {
            character = Action.WALK;
            speed = 2;
            pspeed = 1;
            dir = LEFT;
        }
    }
}
