package android.eric.kaiyue.secretary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;


public class ReminderActivity extends AppCompatActivity {

    private EditText mDateEditText;
    private EditText mTimeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        mDateEditText = findViewById(R.id.textEdit_Date);
        mTimeEditText = findViewById(R.id.textEdit_Time);
    }

    public void showCalendar(View view) {
        DialogFragment newFragment = new DateFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.calendar));
    }

    public void showClock(View view) {
        DialogFragment newFragment = new TimeFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.clock));
    }

    public void processDateResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string +
                "/" + year_string);

        mDateEditText.setText(dateMessage);
    }

    public void processTimeResult(int hourOfDay, int minute) {
        String hourOfDay_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String dateMessage = (hourOfDay_string
                + ":" + minute_string);

        mTimeEditText.setText(dateMessage);
    }
}
