package com.coderyo.d20231026;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private ListView lstPrefer;
    String[] Balls= new String[] {"籃球","足球","棒球","其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult=(TextView) findViewById(R.id.txtResult);
        lstPrefer=(ListView) findViewById(R.id.lstPrefer);
        ArrayAdapter<String> adapterBalls=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Balls);
        lstPrefer.setAdapter(adapterBalls);
        lstPrefer.setOnItemClickListener(lstPreferListener);
    }

    private ListView.OnItemClickListener lstPreferListener=
            new ListView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // 顯示 ListView 的選項內容
                    String sel=parent.getItemAtPosition(position).toString();
                    txtResult.setText("我最喜歡的球類運動是 : " + sel);
                }
            };
}