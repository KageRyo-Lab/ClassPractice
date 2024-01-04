package com.coderyo.d20240104;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private DecimalFormat df = new DecimalFormat("####.###");

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.textView1);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            mSensorManager.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private long start;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        double q = Math.sqrt(x*x + y*y + z*z);

        String tmp = "加速度Gx負值: " + df.format(x) + "\n" +
                "加速度Gy負值: " + df.format(y) + "\n" +
                "加速度Gz負值: " + df.format(z) + "\n" + q;

        if( q*q > Math.pow(1.05*SensorManager.GRAVITY_EARTH, 2) ) {
            if(start == 0) start = System.currentTimeMillis();             // 開始計時
        }
        else {  // 中間停下來
            if(start!=0) {
                long period = System.currentTimeMillis() - start;
                if(period > 1000) { // 超過1秒
                    Log.e("Sensor_1", "=== Shaking ===");
                    Toast.makeText(this, "=== Shaking ===", Toast.LENGTH_SHORT).show();
                }
                start = 0;      // 歸零
            }
        }

        Log.i("Sensor_1a", tmp);

        tv.setText(tmp);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
