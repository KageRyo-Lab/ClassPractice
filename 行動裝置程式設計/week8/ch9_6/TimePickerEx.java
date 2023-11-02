package com.coderyo.d20231102;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerEx extends Activity {
	private static TextView tvTimeDisplay;
	private Button btnPickTime;
	private static int mHour;
	private static int mMinute;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		updateDisplay();
	}

	private static void updateDisplay() {
		StringBuilder sb = new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute));
		tvTimeDisplay.setText(sb);
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	private void findViews() {
		tvTimeDisplay = (TextView) findViewById(R.id.tvTimeDisplay);
		btnPickTime = (Button) findViewById(R.id.btnPickTime);
		btnPickTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TimePickerFragment timePickerFragment = new TimePickerFragment();
				FragmentManager fm = getFragmentManager();
				timePickerFragment.show(fm, "timePicker");
			}
		});
	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			TimePickerDialog timePickerDialog = new TimePickerDialog(
					getActivity(), this, mHour, mMinute, false);
			return timePickerDialog;
		}

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay();
		}
	}
}