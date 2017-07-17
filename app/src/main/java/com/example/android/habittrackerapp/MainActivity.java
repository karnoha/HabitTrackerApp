package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        insertRun();
        insertRun2();
        displayDatabaseInfo();
    }

    private void insertRun() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_DISTANCE, "12");
        values.put(HabitEntry.COLUMN_AVG_HB, "147");
        values.put(HabitEntry.COLUMN_LOCATION, "Hostivice - Palouky");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "New row ID: " + newRowId);
    }


    private void insertRun2() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_DISTANCE, "9");
        values.put(HabitEntry.COLUMN_AVG_HB, "158");
        values.put(HabitEntry.COLUMN_LOCATION, "Chyne");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "New row ID: " + newRowId);
    }

    private void displayDatabaseInfo() {
        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_DISTANCE,
                HabitEntry.COLUMN_AVG_HB,
                HabitEntry.COLUMN_LOCATION
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        TextView displayView = (TextView) findViewById(R.id.textView);

        try {
            displayView.setText("List of all runs. Total: " + cursor.getCount() + "\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_DISTANCE + " - " +
                    HabitEntry.COLUMN_AVG_HB + " - " +
                    HabitEntry.COLUMN_LOCATION + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int distColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DISTANCE);
            int hbColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_AVG_HB);
            int locColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_LOCATION);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                int currentDistance = cursor.getInt(distColumnIndex);
                int currentHB = cursor.getInt(hbColumnIndex);
                String currentLocation = cursor.getString(locColumnIndex);

                displayView.append("\n" +
                        currentID + " - " +
                        currentDistance + " - " +
                        currentHB + " - " +
                        currentLocation);
            }

        } finally {
            cursor.close();
        }

    }
}
