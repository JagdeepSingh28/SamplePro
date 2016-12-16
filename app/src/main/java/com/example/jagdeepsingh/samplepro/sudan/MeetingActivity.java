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

public class MeetingActivity extends AppCompatActivity {

    private long time;
    AlertDialog alertDialogDate;
    AlertDialog alertDialogTime;
    AlertDialog alertDialogsubPackage;
    int mMonth;
    int mDayOfMonth;
    int mYear;
    private Integer currentHour;
    private Integer currentMinute;
    TextView time_tv;
    String selectedPackage;
    Button submit_btn;
    Spinner places_spinner;
    Spinner num_guest_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        time_tv = (TextView) findViewById(R.id.time_tv);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        places_spinner = (Spinner)findViewById(R.id.places_spinner);
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

    public void packageSelected(View view){
        packageSelectedWithColor(view,false);
    }

    void resetColor(){
        (findViewById(R.id.veg_package_tv)).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        (findViewById(R.id.non_veg_package_tv)).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        (findViewById(R.id.full_package_tv)).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        (findViewById(R.id.veg_package2_tv)).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        selectedPackage =  "";
    }

    public void packageSelectedWithColor(View view,boolean isSelected){
        if(isSelected){
            resetColor();
            (view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            selectedPackage =  ((TextView) view).getText().toString();
            return;
        }
        switch (view.getId()){
            case R.id.veg_package_tv:
                String[] package1 = new String[]{"subpackage11","subpackage12","subpackage13","subpackage14"};
                showSubpackageDialog(package1,view);
                break;

            case R.id.non_veg_package_tv:
                String[] package2 = new String[]{"subpackage21","subpackage22","subpackage23","subpackage24"};
                showSubpackageDialog(package2,view);
                break;

            case R.id.full_package_tv:
                String[] package3 = new String[]{"subpackage31","subpackage32","subpackage33","subpackage34"};
                showSubpackageDialog(package3,view);
                break;

            case R.id.veg_package2_tv:
                String[] package4 = new String[]{"subpackage41","subpackage42","subpackage43","subpackage44"};
                showSubpackageDialog(package4,view);
                break;
        }
    }

    private void showSubpackageDialog(String[] subPackage,final View view) {
        final View subPackageDialogView = View.inflate(this, R.layout.package_info, null);

        alertDialogsubPackage = new AlertDialog.Builder(this).create();

        ((TextView) subPackageDialogView.findViewById(R.id.subpackage1)).setText(subPackage[0]);
        ((TextView) subPackageDialogView.findViewById(R.id.subpackage2)).setText(subPackage[1]);
        ((TextView) subPackageDialogView.findViewById(R.id.subpackage3)).setText(subPackage[2]);
        ((TextView) subPackageDialogView.findViewById(R.id.subpackage4)).setText(subPackage[3]);

        subPackageDialogView.findViewById(R.id.date_time_set_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                packageSelectedWithColor(view,true);
                alertDialogsubPackage.dismiss();
            }});

        subPackageDialogView.findViewById(R.id.date_time_cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogsubPackage.dismiss();
            }
        });
        alertDialogsubPackage.setView(subPackageDialogView);
        alertDialogsubPackage.show();
    }
}
