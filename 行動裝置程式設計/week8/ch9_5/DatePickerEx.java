package com.coderyo.d20231102;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerEx extends Activity {
	private static TextView tvDateDisplay;
	private Button btnPickDate;
	private static int mYear;
	private static int mMonth;
	private static int mDay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
	}

	private static void updateDisplay() {
		StringBuilder sb = new StringBuilder().append(mYear).append("-")
				.append(pad(mMonth + 1)).append("-").append(pad(mDay));
		tvDateDisplay.setText(sb);
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	private void findViews() {
		tvDateDisplay = (TextView) findViewById(R.id.tvDateDisplay);
		btnPickDate = (Button) findViewById(R.id.btnPickDate);
		btnPickDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerFragment datePickerFragment = new DatePickerFragment();
				FragmentManager fm = getFragmentManager();
				datePickerFragment.show(fm, "datePicker");
			}
		});
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			DatePickerDialog datePickerDialog = new DatePickerDialog(
					getActivity(), this, mYear, mMonth, mDay);
			return datePickerDialog;
		}

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			mYear = year;
			mMonth = month;
			mDay = day;
			updateDisplay();
		}
	}
}