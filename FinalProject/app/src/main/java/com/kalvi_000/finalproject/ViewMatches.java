package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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


    }
}
