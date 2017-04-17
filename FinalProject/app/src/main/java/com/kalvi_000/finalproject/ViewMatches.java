package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    Intent callUpdateView;

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

        callUpdateView = new Intent(this, UpdateViewMatches.class);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        //can use position+1 as the index of the database item that we want to view/edit
        //seems like it would actually be a good place to implement fragments possibly
        //lets  make a toast to test access of the element that we want.
        cursor = db.query(DB_TableName, null, "_id=" + Integer.toString(position+1), null, null, null, null);
        cursor.moveToFirst(); // initially set at 0 or nothing
        String tempFill;
        dbID = cursor.getInt(0);
        dbPlayLevel = cursor.getString(1);
        dbName = cursor.getString(2);
        dbDeckPlayed = cursor.getString(3);
        dbOpponent = cursor.getString(4);
        dbOpponentDeck = cursor.getString(5);
        dbResult = cursor.getString(6);
        tempFill = dbPlayLevel + ": " + dbName +" (" + dbDeckPlayed + ") vs " + dbOpponent + " (" + dbOpponentDeck + ") Result: " + dbResult;
        Toast.makeText(this, tempFill, Toast.LENGTH_SHORT).show();

        //need to create handling of the non existent fragment and packaging the data to be sent to the other intent.
        callUpdateView.putExtra("Database Position", position+1);
        startActivity(callUpdateView);
    }

}
