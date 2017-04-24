package com.kalvi_000.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
//import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
//import android.widget.Toast;

/**
 * Created by Kevin on 4/16/2017.
 * Used to Update and View match information
 */

public class UpdateViewMatches extends Activity implements DatabaseConstants, CompoundButton.OnCheckedChangeListener{

    private int positionInDB;

    //database stuff
    private SQLiteDatabase db;
    private Cursor cursor;
    //stuff for getting info from db
    private int dbID;
    private String dbName;
    private String dbDeckPlayed;
    private String dbOpponent;
    private String dbOpponentDeck;
    private String dbResult;
    private String dbPlayLevel;

    //UI elements
    private RadioButton casualRB;
    private RadioButton testingRB;
    private RadioButton competitiveRB;
    private EditText name;
    private EditText opponent;
    private EditText deckPlayed;
    private EditText opponentDeck;
    private Spinner resultSpinner;
    private String playLevel = "";

    //intent for getting back
    private Intent returnToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_view_match);

        //get the vale of the position in the database that we want to access.
        positionInDB = getIntent().getIntExtra("Database Position",0);

        //connect all our UI elements
        inflateObjects();

        //database stuff
        EventsData events;
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database

        //do database stuff
        dbStuff();

        //do stuff to set our spinner
        spinnerStuff();

        returnToList = new Intent();
    }

    //go back to previous screen
    public void goToList(@SuppressWarnings("UnusedParameters") View view){
        finish();
    }

    public void updateClick(@SuppressWarnings("UnusedParameters") View view){
        //variable to see if any fields are blank
        boolean incomplete = false;

        //will display error messages if any of the fields have been left empty
        String nameString = name.getText().toString();
        if(TextUtils.isEmpty(nameString)){
            name.setError("Name cannot be empty");
            incomplete = true;
        }
        String deckString = deckPlayed.getText().toString();
        if(TextUtils.isEmpty(deckString)){
            deckPlayed.setError("Deck cannot be empty");
            incomplete = true;
        }
        String opponentString = opponent.getText().toString();
        if(TextUtils.isEmpty(opponentString)){
            opponent.setError("Opponent cannot be empty");
            incomplete = true;
        }
        String opponentDeckString = opponentDeck.getText().toString();
        if(TextUtils.isEmpty(opponentDeckString)){
            opponentDeck.setError("Opponent's Deck cannot be empty");
            incomplete = true;
        }
        if(!casualRB.isChecked() && !testingRB.isChecked() && !competitiveRB.isChecked()){
            incomplete = true;
        }
        if(!incomplete) {
            updateInformation();
        }
    }

    //confirmation dialog instructions found within the AndroidSDK documentation and figuring stuff out in Android Studio
    private void confirmationDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Delete this match?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteDBEntry();
                    }})
                .setNegativeButton("Cancel", null).show();

    }

    //set the playe level radial buttons
    private void setPlayLevel(String playLevelFromDB){
        playLevel = playLevelFromDB;
        switch (playLevelFromDB){
            case "Casual":
                casualRB.setChecked(true);
                break;
            case "Testing":
                testingRB.setChecked(true);
                break;
            case "Competitive":
                competitiveRB.setChecked(true);
                break;
        }
    }
    //set the result spinner

    private void setResultSpinner(String resultFromDB){
        switch (resultFromDB){
            case "2-0":
                resultSpinner.setSelection(0);
                break;
            case "2-1":
                resultSpinner.setSelection(1);
                break;
            case "1-2":
                resultSpinner.setSelection(2);
                break;
            case "0-2":
                resultSpinner.setSelection(3);
                break;
            case "0-1-1":
                resultSpinner.setSelection(4);
                break;
            case "1-0-1":
                resultSpinner.setSelection(5);
                break;
            case "1-1-1":
                resultSpinner.setSelection(6);
                break;

        }
    }

    //update the databse entry to the new results the user has entered
    private void updateInformation(){
        ContentValues someValues = new ContentValues(); // this is a single row in the database.
        someValues.put("playlevel", playLevel);
        someValues.put("name", name.getText().toString());
        someValues.put("deckplayed", deckPlayed.getText().toString());
        someValues.put("opponent", opponent.getText().toString());
        someValues.put("opponentdeck", opponentDeck.getText().toString());
        someValues.put("result", resultSpinner.getSelectedItem().toString());
        db.update(DB_TableName, someValues, "_id=" + Integer.toString(positionInDB),null);

        setResult(Activity.RESULT_OK, returnToList);
        finish();
    }

    //update from the results of the radial buttons
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.casualEditRadioButton:
                if(isChecked){
                    playLevel="Casual";
                }
                break;
            case R.id.testingEditRadioButton:
                if(isChecked){
                    playLevel="Testing";
                }
                break;
            case R.id.competitiveEditRadioButton:
                if(isChecked){
                    playLevel="Competitive";
                }
                break;

        }
    }

    //connect all th eonjects in the UI
    private void inflateObjects(){
        //connect all the edit texts and buttons
        casualRB = (RadioButton) findViewById(R.id.casualEditRadioButton);
        casualRB.setOnCheckedChangeListener(this);
        testingRB = (RadioButton) findViewById(R.id.testingEditRadioButton);
        testingRB.setOnCheckedChangeListener(this);
        competitiveRB = (RadioButton) findViewById(R.id.competitiveEditRadioButton);
        competitiveRB.setOnCheckedChangeListener(this);
        //connect edit texts
        name = (EditText) findViewById(R.id.nameEdit);
        deckPlayed = (EditText) findViewById(R.id.deckPlayedEdit);
        opponent = (EditText) findViewById(R.id.opponentEdit);
        opponentDeck = (EditText) findViewById(R.id.opponentDeckEdit);
        //connect the spinner
        resultSpinner = (Spinner) findViewById(R.id.ResultSpinnerEdit);
    }

    //do database stuff
    private void dbStuff(){
        //grab stuff from the database and populate all of our fields
        cursor = db.query(DB_TableName, null, "_id=" + Integer.toString(positionInDB), null, null, null, null);
        cursor.moveToFirst(); // initially set at 0 or nothing
        dbID = cursor.getInt(0);
        dbPlayLevel = cursor.getString(1);
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
    }

    //populate the spinner
    private void spinnerStuff(){
        //spinner stuff
        resultSpinner = (Spinner) findViewById(R.id.ResultSpinnerEdit);
        ArrayAdapter<CharSequence> resultAdapter = ArrayAdapter.createFromResource(this, R.array.Results, android.R.layout.simple_spinner_item);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultSpinner.setAdapter(resultAdapter);
        setResultSpinner(dbResult);
    }

    //delete the current entry in the databases the exit the activity
    private void deleteDBEntry(){
        //if I did this wrong it will delete the whole database
        db.delete(DB_TableName,"_id=" + Integer.toString(positionInDB), null);
        setResult(Activity.RESULT_OK, returnToList);
        finish();

    }

    //auto generated suppress code
    //play  a noise whenever the attempt to delete a database entry
    public void deleteMatch(@SuppressWarnings("UnusedParameters") View view){
        final MediaPlayer warning = MediaPlayer.create(this, R.raw.warning);
        warning.start();
        confirmationDialog();
    }

    //preserve data on phone rotation
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("Name",name.getText().toString());
        savedInstanceState.putString("DeckPlayed",deckPlayed.getText().toString());
        savedInstanceState.putString("Opponent",opponent.getText().toString());
        savedInstanceState.putString("OpponentDeck",opponentDeck.getText().toString());
        savedInstanceState.putString("PlayLevel", playLevel);
        savedInstanceState.putInt("Result", resultSpinner.getSelectedItemPosition());

        //save the data
        super.onSaveInstanceState(savedInstanceState);
    }

    //Restore Data
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restore the data

        name.setText(savedInstanceState.getString("Name"));
        deckPlayed.setText(savedInstanceState.getString("DeckPlayed"));
        opponent.setText(savedInstanceState.getString("Opponent"));
        opponentDeck.setText(savedInstanceState.getString("OpponentDeck"));
        playLevel = savedInstanceState.getString("Name");
        switch(playLevel){
            case "Casual":
                casualRB.setChecked(true);
                break;
            case "Testing":
                testingRB.setChecked(true);
                break;
            case "Competitive":
                competitiveRB.setChecked(true);
                break;
        }
        resultSpinner.setSelection(savedInstanceState.getInt("Result"));

    }
}
