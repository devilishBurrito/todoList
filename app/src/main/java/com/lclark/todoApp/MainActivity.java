package com.lclark.todoApp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private String words;
    private EditText input;
    private TextView day;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String[] weekdays;
    private int date;
    private Button back;
    private Button save;
    private Button forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = 1;

        day = (TextView) findViewById(R.id.activity_main_day);

        input = (EditText) findViewById((R.id.activity_main_input));

        back = (Button) findViewById(R.id.activity_main_left);
        save = (Button) findViewById(R.id.activity_main_save);
        forward = (Button) findViewById(R.id.activity_main_right);

        setdays();
        loadPrefs();

        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        save.setOnClickListener(this);

        back.setText(weekdays[date - 1]);
        save.setText("SAVE");
        forward.setText(weekdays[date + 1]);
        day.setText(weekdays[date]);
        input.setHint("Your Plans for " + weekdays[date]);

    }

    private void setdays() {
        weekdays = new String[7];
        weekdays[0] = "Sunday";
        weekdays[1] = "Monday";
        weekdays[2] = "Tuesday";
        weekdays[3] = "Wednesday";
        weekdays[4] = "Thursday";
        weekdays[5] = "Friday";
        weekdays[6] = "Saturday";
    }

    public void loadPrefs() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String tasks = preferences.getString(weekdays[date], null);
        editor = preferences.edit();
        input.setText(tasks);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.activity_main_save) {
            words = String.valueOf(input.getText());
            editor.putString(weekdays[date], words);
            editor.commit();
            input.setText(preferences.getString(weekdays[date], null));
            Toast.makeText(MainActivity.this, "Your To-do List Has Been Updated", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.activity_main_left) {
            if (date == 0) {
                date = 6;
                back.setText(weekdays[date - 1]);
                day.setText(weekdays[date]);
                forward.setText(weekdays[0]);
                input.setText(preferences.getString(weekdays[date], null));
            } else if (date == 1) {
                date -= 1;
                back.setText(weekdays[6]);
                day.setText(weekdays[date]);
                forward.setText(weekdays[date + 1]);
                input.setText(preferences.getString(weekdays[date], null));
            } else {
                date -= 1;
                back.setText(weekdays[date - 1]);
                day.setText(weekdays[date]);
                forward.setText(weekdays[date + 1]);
                input.setText(preferences.getString(weekdays[date], null));
            }
            input.setHint("Your Plans for " + weekdays[date]);

        }
        if (v.getId() == R.id.activity_main_right) {
            if (date == 5) {
                date += 1;
                back.setText(weekdays[date - 1]);
                day.setText(weekdays[date]);
                forward.setText(weekdays[0]);
                input.setText(preferences.getString(weekdays[date], null));
            } else if (date == 6) {
                date = 0;
                back.setText(weekdays[6]);
                day.setText(weekdays[date]);
                forward.setText(weekdays[date + 1]);
                input.setText(preferences.getString(weekdays[date], null));
            } else {
                date += 1;
                back.setText(weekdays[date - 1]);
                day.setText(weekdays[date]);
                forward.setText(weekdays[date + 1]);
                input.setText(preferences.getString(weekdays[date], null));

            }
            input.setHint("Your Plans for " + weekdays[date]);

        }


    }
}
