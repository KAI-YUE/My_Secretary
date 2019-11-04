package android.eric.kaiyue.secretary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Reminder(View view){
        Intent ReminderIntent = new Intent(this,
                ReminderActivity.class);
        startActivity(ReminderIntent);
    }

    public void Weather(View view){
        Intent WeatherIntent = new Intent(this,
                WeatherActivity.class);
        startActivity(WeatherIntent);
    }
}
