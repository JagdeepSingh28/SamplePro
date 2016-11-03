package com.example.jagdeepsingh.samplepro.contentProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;

public class CountryEdit extends AppCompatActivity implements View.OnClickListener {

    private Spinner continentList;
    private Button save, delete;
    private String mode;
    private EditText code, name;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_edit);

        if(this.getIntent().getExtras()!=null){
            Bundle bundle = this.getIntent().getExtras();
            mode = bundle.getString("mode");
        }

        // get references to the buttons and attach listeners
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

        code = (EditText) findViewById(R.id.code);
        name = (EditText) findViewById(R.id.name);


        // create a dropdown for users to select various continents
        continentList = (Spinner) findViewById(R.id.continentList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.continent_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        continentList.setAdapter(adapter);

        // if in add mode disable the delete option
        if(mode.trim().equalsIgnoreCase("add")){
            delete.setEnabled(false);
        }
        // get the rowId for the specific country
        else{
            Bundle bundle = this.getIntent().getExtras();
            id = bundle.getString("rowId");
            loadCountryInfo();
        }

    }


    private void loadCountryInfo() {
        String[] projection = {
                CountriesDb.KEY_ROW_ID,
                CountriesDb.KEY_CODE,
                CountriesDb.KEY_NAME,
                CountriesDb.KEY_CONTINENT};
        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + id);
        Cursor cursor = getContentResolver().query(uri, projection, null, null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            String myCode = cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_CODE));
            String myName = cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_NAME));
            String myContinent = cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_CONTINENT));
            code.setText(myCode);
            name.setText(myName);
            continentList.setSelection(getIndex(continentList, myContinent));
        }

    }

    // this sets the spinner selection based on the value
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public void onClick(View v) {

        String myContinent  = continentList.getSelectedItem().toString();
        String myCode       = code.getText().toString();
        String myName       = name.getText().toString();

        if(TextUtils.isEmpty(myCode) || TextUtils.isEmpty(myName)){
            Toast.makeText(this, "Fill All details", Toast.LENGTH_SHORT).show();
        }

        switch (v.getId()){
            case R.id.save:
                ContentValues values = new ContentValues();
                values.put(CountriesDb.KEY_CODE,myCode);
                values.put(CountriesDb.KEY_NAME,myName);
                values.put(CountriesDb.KEY_CONTINENT,myContinent);

                if(mode.equalsIgnoreCase("add")){
                    getContentResolver().insert(MyContentProvider.CONTENT_URI,values);
                }else{
                    Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + id) ;
                    getContentResolver().update(uri,values,null,null);
                }

                finish();
                break;

            case R.id.delete:
                Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + id);
                getContentResolver().delete(uri, null, null);
                finish();
                break;
        }

    }
}
