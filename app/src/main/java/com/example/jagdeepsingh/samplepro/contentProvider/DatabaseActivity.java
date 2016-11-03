package com.example.jagdeepsingh.samplepro.contentProvider;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;

public class DatabaseActivity extends Activity
        implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        displayListView();

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // starts a new Intent to add a Country
                Intent countryEdit = new Intent(getBaseContext(), CountryEdit.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                countryEdit.putExtras(bundle);
                startActivity(countryEdit);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0,null,this);
    }

    private void displayListView() {

        String[] columns = new String[]{
                CountriesDb.KEY_CODE,
                CountriesDb.KEY_NAME,
                CountriesDb.KEY_CONTINENT
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.code,
                R.id.name,
                R.id.continent,
        };

        dataAdapter = new SimpleCursorAdapter(this,
                R.layout.country_info,
                null,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.countryList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //Ensures a loader is initialized and active.
        getLoaderManager().initLoader(0, null, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // display the selected country
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_CODE));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

                String rowId =
                        cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_ROW_ID));

                // starts a new Intent to update/delete a Country
                // pass in row Id to create the Content URI for a single row
                Intent countryEdit = new Intent(getBaseContext(), CountryEdit.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "update");
                bundle.putString("rowId", rowId);
                countryEdit.putExtras(bundle);
                startActivity(countryEdit);

            }
        });

    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                CountriesDb.KEY_ROW_ID,
                CountriesDb.KEY_CODE,
                CountriesDb.KEY_NAME,
                CountriesDb.KEY_CONTINENT};

        CursorLoader cursorLoader = new CursorLoader(this,
                MyContentProvider.CONTENT_URI,
                projection,
                null,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        // swap the data from new cursor to the adapter
        dataAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        dataAdapter.swapCursor(null);
    }

}
