package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
        Vector<String> dataBaseVectors= new Vector<>();

        cursor = db.query(DB_TableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {  //move to next row, if possible
            String tempFill;
            dbID = cursor.getInt(0);
            dbPlayLevel = cursor.getInt(1);
            dbName =cursor.getString(2);
            dbDeckPlayed = cursor.getString(3);
            dbOpponent = cursor.getString(4);
            dbOpponentDeck = cursor.getString(5);
            dbResult = cursor.getString(6);
            tempFill = dbID + " " + dbPlayLevel + " " + dbName + " " + dbDeckPlayed + " " + dbOpponent  + " " + dbOpponentDeck + " " + dbResult;
            dataBaseVectors.addElement(tempFill);
        }




    }
}
