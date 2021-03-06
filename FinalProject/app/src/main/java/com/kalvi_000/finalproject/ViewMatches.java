package com.kalvi_000.finalproject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Toast;

import java.util.ArrayList;
//import java.util.Vector;

/**
 * Created by kalvi_000
 * Used to View all of the matches that have been entered
 */

public class ViewMatches extends ListActivity implements DatabaseConstants {

    //database stuff
    private SQLiteDatabase db;
    private Cursor cursor;
    private int dbID;
    private String dbName;
    private String dbDeckPlayed;
    private String dbOpponent;
    private String dbOpponentDeck;
    private String dbResult;
    private String dbPlayLevel;
    private ArrayList<Integer> dbIDList = new ArrayList<>();

    private Intent callUpdateView;

    private static final int REQUEST_CODE = 10;

    public static final String VIEW = BuildConfig.APPLICATION_ID + ".OPEN_DYNAMIC_SHORTCUT_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshInformation();
        callUpdateView = new Intent(this, UpdateViewMatches.class);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        callUpdateView.putExtra("Database Position", dbIDList.get(position));
        startActivityForResult(callUpdateView,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                refreshInformation();
            }
            else{
                Log.d("Brenneman","Shouldn't be here");
                refreshInformation();
            }
        }
    }

    private void refreshInformation(){
        //database stuff
        EventsData events;
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
            dbIDList.add(dbID); //used to pass the id of the selected element along when needed
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
