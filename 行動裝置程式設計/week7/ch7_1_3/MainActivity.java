package com.coderyo.d20231026;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private ListView lstPrefer;
    private Button btnDo;
    String[] Balls= new String[] {"籃球","足球","棒球","其他"};
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDo=(Button) findViewById(R.id.btnDo);
        txtResult=(TextView)findViewById(R.id.txtResult);
        lstPrefer=(ListView)findViewById(R.id.lstPrefer);

        ArrayAdapter<String> adapterBalls = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, Balls);
        lstPrefer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstPrefer.setAdapter(adapterBalls);

        count = adapterBalls.getCount();  // 取得選取項目總數
        btnDo.setOnClickListener(btnDoListener);
        lstPrefer.setOnItemClickListener(lstPreferListener);
    }

    // onClick()
    private Button.OnClickListener btnDoListener=new Button.OnClickListener(){
        public void onClick(View v){
            String selAll="";
            for(int p = 0; p < count; p++){
                if (lstPrefer.isItemChecked(p)) // 巳核選
                    selAll += Balls[p] + " ";
            }
            txtResult.setText("我最喜歡的球類運動是 :" + selAll);
        }
    };

    // onItemClick
    private ListView.OnItemClickListener lstPreferListener=
            new ListView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String sel=parent.getItemAtPosition(position).toString();
                    if (lstPrefer.isItemChecked(position)){ // 巳核選
                        setTitle("目前選取： " + sel);
                    }else{
                        setTitle("取消選取： " + sel);
                    }
                }
            };
}