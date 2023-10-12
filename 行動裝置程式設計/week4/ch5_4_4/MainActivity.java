package com.coderyo.d20231012_6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private Spinner spnPrefer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult=(TextView) findViewById(R.id.txtResult);
        spnPrefer=(Spinner) findViewById(R.id.spnPrefer);
        // 建立ArrayAdapter
        ArrayAdapter<CharSequence> adapterBalls = ArrayAdapter.createFromResource(
                this, R.array.Balls,android.R.layout.simple_spinner_item);

        // 設定Spinner顯示的格式
        adapterBalls.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);

        // 設定Spinner的資料來源
        spnPrefer.setAdapter(adapterBalls);

        // 設定 spnPrefer 元件 ItemSelected 事件的 listener 為  spnPreferListener
        spnPrefer.setOnItemSelectedListener(spnPreferListener);
    }

    //  定義  onItemSelected 方法
    private Spinner.OnItemSelectedListener spnPreferListener=
            new Spinner.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View v,
                                           int position, long id) {
                    String sel=parent.getSelectedItem().toString();
                    txtResult.setText(sel);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            };
}