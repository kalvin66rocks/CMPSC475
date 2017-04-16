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
    int dbID, dbPlayLevel;
    String dbName, dbDeckPlayed, dbOpponent, dbOpponentDeck, dbResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //database stuff
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database
        ArrayList<String> dbStrings = new ArrayList<>();
        ArrayList<String> dbIDList = new ArrayList<>();
        ArrayList<String> dbPlayLevelList = new ArrayList<>();
        ArrayList<String> dbNameList = new ArrayList<>();
        ArrayList<String> dbDeckPlayedList = new ArrayList<>();
        ArrayList<String> dbOpponentList = new ArrayList<>();
        ArrayList<String> dbOpponentDeckList = new ArrayList<>();
        ArrayList<String> dbResultList = new ArrayList<>();

        cursor = db.query(DB_TableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {  //move to next row, if possible
            //could use this to loop through the database filling an array list for each of elements we want, using a property of strings to make them all a standard length,
            //from there it would be easily to keep tht columns aligned
            // it would make the listener for onItemSelected a little more ridiculous, but would prevent it from looking like shit
            String tempFill;
            dbID = cursor.getInt(0);
            dbIDList.add(Integer.toString(dbID));
            dbPlayLevel = cursor.getInt(1);
            dbPlayLevelList.add(Integer.toString(dbPlayLevel));
            dbName = cursor.getString(2);
            dbNameList.add(dbName);
            dbDeckPlayed = cursor.getString(3);
            dbDeckPlayedList.add(dbDeckPlayed);
            dbOpponent = cursor.getString(4);
            dbOpponentList.add(dbOpponent);
            dbOpponentDeck = cursor.getString(5);
            dbOpponentDeckList.add(dbOpponentDeck);
            dbResult = cursor.getString(6);
            dbResultList.add(dbResult);
            tempFill = "ID: " + dbID + " Play Level: " + dbPlayLevel + " Name: " + dbName + " Deck: " + dbDeckPlayed + " Opponent: " + dbOpponent + " Opponent's Deck " + dbOpponentDeck + " Result: " + dbResult;
            dbStrings.add(tempFill);
        }
        setListAdapter(new ArrayAdapter<>(this, R.layout.view_matches, R.id.databaseEntries, dbStrings));

    }
}
