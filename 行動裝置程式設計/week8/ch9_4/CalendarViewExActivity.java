package com.coderyo.d20231102;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.CalendarView.OnDateChangeListener;

public class CalendarViewExActivity extends Activity {
	private TextView tvDateDisplay;
	private CalendarView calendarView; 
    private int mYear;
    private int mMonth;
    private int mDay;
	
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

	private void updateDisplay() {
		tvDateDisplay.setText(
			new StringBuilder()
					.append(mYear).append("-")					
					.append(mMonth + 1).append("-")
					.append(mDay));
	}

	private void findViews() {
		tvDateDisplay = (TextView)findViewById(R.id.tvDateDisplay);
		calendarView = (CalendarView)findViewById(R.id.calendarView);
		calendarView.setOnDateChangeListener(new OnDateChangeListener() {			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				mYear = year;
				mMonth = month;
				mDay = dayOfMonth;
				updateDisplay();
			}
		});
	}
}