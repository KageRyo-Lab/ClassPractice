package com.coderyo.d20231102;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteEx extends Activity {
    private AutoCompleteTextView acCountry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
    }

	private void findViews() {
		acCountry = (AutoCompleteTextView)findViewById(R.id.acCountry);
		String[] countries = 
			getResources().getStringArray(R.array.countries_array);
		ArrayAdapter<String> adapterCountry = 
			new ArrayAdapter<String>(this, R.layout.list_item, countries);
		acCountry.setAdapter(adapterCountry);
	}	
}