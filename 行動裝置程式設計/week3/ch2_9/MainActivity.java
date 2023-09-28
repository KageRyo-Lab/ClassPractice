package com.coderyo.d20230928_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtMile;
    private TextView txtKm;
    private Button btnTran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMile=(EditText)findViewById(R.id.edtMile);
        txtKm=(TextView)findViewById(R.id.txtKm);
        btnTran=(Button)findViewById(R.id.btnTran);
        btnTran.setOnClickListener(btnTranListener);
    }

    private Button.OnClickListener btnTranListener=new Button.OnClickListener(){
        public void onClick(View v){
            double miles=Double.parseDouble(edtMile.getText().toString());
            double km=1.61*miles;
            txtKm.setText("時速= " + km + " 公里");
        }
    };
}
