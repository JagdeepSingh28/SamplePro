package com.example.jagdeepsingh.samplepro.sudan;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.jagdeepsingh.samplepro.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DinnerActivity extends AppCompatActivity {

    Spinner places_spinner;
    Spinner num_guest_spinner;
    int mMonth;
    int mDayOfMonth;
    int mYear;
    private Integer currentHour;
    private Integer currentMinute;
    TextView time_tv;

    private long time;
    AlertDialog alertDialogDate;
    AlertDialog alertDialogTime;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        time_tv = (TextView) findViewById(R.id.time_tv);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        places_spinner = (Spinner)findViewById(R.id.restaurant_spinner);
        places_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        num_guest_spinner = (Spinner)findViewById(R.id.num_guest_spinner);
        num_guest_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setDialogs();
    }

    private void setDialogs() {

        final View dateDialogView = View.inflate(this, R.layout.date_time_picker, null);
        final View timeDialogView = View.inflate(this, R.layout.time_picker, null);

        alertDialogDate = new AlertDialog.Builder(this).create();
        alertDialogTime = new AlertDialog.Builder(this).create();

        dateDialogView.findViewById(R.id.date_time_set_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dateDialogView.findViewById(R.id.date_picker);
                datePicker.setMinDate(System.currentTimeMillis() - 1000);

                mYear        = datePicker.getYear();
                mMonth       = datePicker.getMonth();
                mDayOfMonth  = datePicker.getDayOfMonth();

                alertDialogDate.dismiss();
                alertDialogTime.show();
            }});

        dateDialogView.findViewById(R.id.date_time_cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogDate.dismiss();
            }
        });
        alertDialogDate.setView(dateDialogView);

        timeDialogView.findViewById(R.id.date_time_set_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePicker timePicker = (TimePicker) timeDialogView.findViewById(R.id.time_picker);

                currentHour = timePicker.getCurrentHour();
                currentMinute = timePicker.getCurrentMinute();

                Calendar calendar = new GregorianCalendar(mYear,
                        mMonth,
                        mDayOfMonth,
                        currentHour,
                        currentMinute);

                time = calendar.getTimeInMillis();
                time_tv.setText(getDate(time, "dd/MM/yyyy hh:mm"));
                alertDialogTime.dismiss();
            }});

        timeDialogView.findViewById(R.id.date_time_cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogTime.dismiss();
            }
        });
        alertDialogTime.setView(timeDialogView);
    }

    public void setDateTime(View view){
        alertDialogDate.show();
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
