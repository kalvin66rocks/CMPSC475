package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by kalvi_000 on 4/16/2017.
 */

public class ViewMatches extends ListActivity implements DatabaseConstants {

    //database stuff
    SQLiteDatabase db;
    Cursor cursor;
    private EventsData events;
    int dbID;
    String dbName, dbDeckPlayed, dbOpponent, dbOpponentDeck, dbResult, dbPlayLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //database stuff
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database
        ArrayList<String> dbStrings = new ArrayList<>();

        cursor = db.query(DB_TableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {  //move to next row, if possible
            //could use this to loop through the database filling an array list for each of elements we want, using a property of strings to make them all a standard length,
            //from there it would be easily to keep tht columns aligned
            // it would make the listener for onItemSelected a little more ridiculous, but would prevent it from looking like shit
            String tempFill;
            dbID = cursor.getInt(0);
            dbPlayLevel = cursor.getString(1);
            dbName = cursor.getString(2);
            dbDeckPlayed = cursor.getString(3);
            dbOpponent = cursor.getString(4);
            dbOpponentDeck = cursor.getString(5);
            dbResult = cursor.getString(6);

            //need to clean up this string, currently to long.
            tempFill = dbPlayLevel + ": " + dbName +" (" + dbDeckPlayed + ") vs " + dbOpponent + " (" + dbOpponentDeck + ") Result: " + dbResult;
            dbStrings.add(tempFill);
        }
        setListAdapter(new ArrayAdapter<>(this, R.layout.view_matches, R.id.databaseEntries, dbStrings));

    }
}
