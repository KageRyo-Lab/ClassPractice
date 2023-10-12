package com.coderyo.d20231012_7;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerEx extends Activity {
	private Spinner spPlace;
	private Spinner spFood;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
	}

	private void findViews() {
		spFood = (Spinner)findViewById(R.id.spFood);
		spFood.setOnItemSelectedListener(listener);

		spPlace = (Spinner)findViewById(R.id.spPlace);
		String[] places = {"Australia", "U.K.", "Japan", "Thailand"};
		ArrayAdapter<String> adapterPlace =
				new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, places);
		adapterPlace.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		spPlace.setAdapter(adapterPlace);
		spPlace.setOnItemSelectedListener(listener);
	}

	Spinner.OnItemSelectedListener listener =
			new Spinner.OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView parent,
										   View view, int pos, long id) {
					Toast.makeText(parent.getContext(),
							parent.getItemAtPosition(pos).toString(),
							Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onNothingSelected(AdapterView parent) {
					Toast.makeText(parent.getContext(),
							"Nothing Selected!",
							Toast.LENGTH_SHORT).show();
				}
			};
}