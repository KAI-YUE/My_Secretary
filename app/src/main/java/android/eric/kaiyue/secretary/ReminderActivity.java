package android.eric.kaiyue.secretary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.mbms.MbmsErrors;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;


public class ReminderActivity extends AppCompatActivity {

    // Member variables wrt edittext
    private EditText mTitleText;
    private EditText mDateEditText;
    private EditText mTimeEditText;

    // Member variables wrt recyclerView
    private ArrayList<Reminder> mReminderData;
    private ReminderAdapter mReminderAdapter;
    private RecyclerView mRecycleView;

    // Member variables wrt alarm clock
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    private int NOTIFICATION_ID = 0;
    private NotificationManager mNotificationManager;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        mTitleText = findViewById(R.id.textEdit_Title);
        mDateEditText = findViewById(R.id.textEdit_Date);
        mTimeEditText = findViewById(R.id.textEdit_Time);

        mRecycleView = findViewById(R.id.reminder_recycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mReminderData = new ArrayList<>();
        mReminderAdapter = new ReminderAdapter(this, mReminderData);
        mRecycleView.setAdapter(mReminderAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            /**
             * Defines the drag and drop functionality.
             *
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the
             *               original one with.
             * @return true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // Get the from and to positions.
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                // Swap the items and notify the adapter.
                Collections.swap(mReminderData, from, to);
                mReminderAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The viewholder being swiped.
             * @param direction The direction it is swiped in.
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // Remove the item from the dataset.
                mReminderData.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                mReminderAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecycleView);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        final PendingIntent notifyPendingIntent =  PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

//        mAlarmManager.setRepeating(mAlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+5000,
//                5000, notifyPendingIntent);
//        mAlarmManager.set(AlarmManager.RTC_WAKEUP, 10000, notifyPendingIntent);
        createNotificationChannel();
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

    public void addItem(View view) {
        String Title = mTitleText.getText().toString();
        String Date = mDateEditText.getText().toString();
        String Time = mTimeEditText.getText().toString();
        Title = Title.isEmpty() ? "No Title" : Title;
        Reminder currentReminder = new Reminder(Title, Date, Time);

        mReminderData.add(currentReminder);
        mReminderAdapter.notifyDataSetChanged();
    }

    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to " +
                    "stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

}
