package com.coderyo.d20231102;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActivityLifeEx extends Activity {
	private Button btnFinish;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        findViews();
    }
 
    private void findViews() {
    	btnFinish = (Button)findViewById(R.id.btnFinish);
    	btnFinish.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
    public void onStart(){
    	super.onStart();
    	Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart(){
    	super.onRestart();
    	Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(){
    	super.onStop();
    	Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
     
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }    
}