package com.coderyo.d20231102_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPage2=(Button)findViewById(R.id.btnPage2);
        btnPage2.setOnClickListener(btnPage2Listener);
    }

    private Button.OnClickListener btnPage2Listener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Second.class);

            String name="David";
            int age=27;
            Double tall=183.5;

            Bundle bundle=new Bundle();
            bundle.putString("NAME", name);
            bundle.putInt("AGE", age);
            bundle.putDouble("TALL", tall);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    };
}
