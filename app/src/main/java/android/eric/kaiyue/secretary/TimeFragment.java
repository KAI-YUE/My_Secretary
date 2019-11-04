package android.eric.kaiyue.secretary;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    /**
     * Creates the time picker dialog with the current time from Calendar.
     *
     * @param savedInstanceState    Saved instance state bundle.

     * @return TimePickerDialog     The time picker dialog.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    /**
     * Grabs the time and converts it to a string to pass
     * to the Main Activity in order to show it with processTimePickerResult().
     *
     * @param timePicker    The time picker view
     * @param hourOfDay     The hour chosen
     * @param minute        The minute chosen
     */
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        ReminderActivity activity = (ReminderActivity) getActivity();
        activity.processTimeResult(hourOfDay, minute);
    }
}
