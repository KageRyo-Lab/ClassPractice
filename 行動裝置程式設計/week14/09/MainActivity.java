package com.coderyo.d20231214



import android.app.Activity;
import android.os.Bundle;

import java.util.List;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	//�ʂ��@ʾ�YӍ��UI�M��    
		final TextView tx1 = (TextView) findViewById(R.id.textView01);   
		//��ϵ�y�����Ы@�øМy��������    
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);    
		//�ĸМy���������Ы@��ȫ���ĸМy�����    
		List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);   
		//�@ʾ�ж��ق��Мy��    
		tx1.setText("���z�yԓ�֙C��" + allSensors.size() + "���Мy���������քe�ǣ�\n");    
		//�@ʾÿ���Мy���ľ��w�YӍ    
		for (Sensor s: allSensors) {    
			String tempString = "\n" + "  �O�����Q��" + s.getName() + "\n" + "  �O��汾��" + s.getVersion() + "\n" + "  �����̣�"   + s.getVendor() + "\n";    
			switch (s.getType()) {    
			case Sensor.TYPE_ACCELEROMETER:    
				tx1.setText(tx1.getText().toString() + s.getType() + " ���ٶȸМy��accelerometer" + tempString);    break;    
			case Sensor.TYPE_GYROSCOPE:    
				tx1.setText(tx1.getText().toString() + s.getType() + " ���݃x�Мy��gyroscope" + tempString);    break;    
			case Sensor.TYPE_LIGHT:    
				tx1.setText(tx1.getText().toString() + s.getType() + " �h���⾀�Мy��light" + tempString);    break;    
			case Sensor.TYPE_MAGNETIC_FIELD:    
				tx1.setText(tx1.getText().toString() + s.getType() + " 늴ň��Мy��magnetic field" + tempString);    break;    
			case Sensor.TYPE_ORIENTATION:    
				tx1.setText(tx1.getText().toString() + s.getType() + " ����Мy��orientation" + tempString);    break;    
			case Sensor.TYPE_PRESSURE:    
				tx1.setText(tx1.getText().toString() + s.getType() + " �����Мy��pressure" + tempString);    break;    
			case Sensor.TYPE_PROXIMITY:    
				tx1.setText(tx1.getText().toString() + s.getType() + " ���x�Мy��proximity" + tempString);    break;    
			case Sensor.TYPE_TEMPERATURE:    
				tx1.setText(tx1.getText().toString() + s.getType() + " �ضȸМy��temperature" + tempString);    break;    
			default:    
				tx1.setText(tx1.getText().toString() + s.getType() + " δ֪�Мy��" + tempString);    break;    
			}    
		}    
	}    
}
