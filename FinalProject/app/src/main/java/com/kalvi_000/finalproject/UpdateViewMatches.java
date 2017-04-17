package com.kalvi_000.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Kevin on 4/16/2017.
 */

public class UpdateViewMatches extends Activity implements DatabaseConstants{

    int positionInDB;

    //database stuff
    SQLiteDatabase db;
    Cursor cursor;
    private EventsData events;
    int dbID;
    String dbName, dbDeckPlayed, dbOpponent, dbOpponentDeck, dbResult, dbPlayLevel;

    //Button updateData;
    RadioButton casualRB,testingRB,competitiveRB;
    EditText name,opponent,deckPlayed,opponentDeck;
    Spinner resultSpinner;
    private String playLevel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_view_match);

        //get the vale of the position in the database that we want to access.
        positionInDB = getIntent().getIntExtra("Database Position",0);

        //connect all the edit texts and buttons
        casualRB = (RadioButton) findViewById(R.id.casualEditRadioButton);
        testingRB = (RadioButton) findViewById(R.id.testingEditRadioButton);
        competitiveRB = (RadioButton) findViewById(R.id.competitiveEditRadioButton);
        //connect edit texts
        name = (EditText) findViewById(R.id.nameEdit);
        deckPlayed = (EditText) findViewById(R.id.deckPlayedEdit);
        opponent = (EditText) findViewById(R.id.opponentEdit);
        opponentDeck = (EditText) findViewById(R.id.opponentDeckEdit);
        //connect the spinner
        resultSpinner = (Spinner) findViewById(R.id.ResultSpinnerEdit);

        //database stuff
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database

        //grab stuff from the database and populate all of our fields
        cursor = db.query(DB_TableName, null, "_id=" + Integer.toString(positionInDB), null, null, null, null);
        cursor.moveToFirst(); // initially set at 0 or nothing
        dbID = cursor.getInt(0);
        dbPlayLevel = cursor.getString(1);
        //this needs handled special based off of a switch statement, will be making a function
        setPlayLevel(dbPlayLevel);
        dbName = cursor.getString(2);
        name.setText(dbName);
        dbDeckPlayed = cursor.getString(3);
        deckPlayed.setText(dbDeckPlayed);
        dbOpponent = cursor.getString(4);
        opponent.setText(dbOpponent);
        dbOpponentDeck = cursor.getString(5);
        opponentDeck.setText(dbOpponentDeck);
        dbResult = cursor.getString(6);
        setResultSpinner(dbResult);
        //this needs handled special based off of a switch statement, will be making a function


    }

    public void goToMain(View view){
        finish();
    }

    public void updateClick(View view){
        confirmationDialog();
    }

    public void confirmationDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Do you really want to whatever?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        updateInformation();

                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }

    public void setPlayLevel(String playLevelfromDB){
        //currently empty
    }

    public void setResultSpinner(String resultFromDB){
        //currently empty
    }

    public void updateInformation(){

    }

}
