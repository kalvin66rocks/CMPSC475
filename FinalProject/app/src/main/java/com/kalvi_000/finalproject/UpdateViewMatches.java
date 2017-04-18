package com.kalvi_000.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Kevin on 4/16/2017.
 */

public class UpdateViewMatches
        extends Activity
        implements DatabaseConstants,
        CompoundButton.OnCheckedChangeListener{

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

    Intent returnToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_view_match);

        //get the vale of the position in the database that we want to access.
        positionInDB = getIntent().getIntExtra("Database Position",0);

        inflateObjects();

        //database stuff
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database

        //grab stuff from the database and populate all of our fields
        cursor = db.query(DB_TableName, null, "_id=" + Integer.toString(positionInDB), null, null, null, null);
        Log.d("Before it Crashes", Integer.toString(positionInDB));
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

        //spinner stuff
        resultSpinner = (Spinner) findViewById(R.id.ResultSpinnerEdit);
        ArrayAdapter<CharSequence> resultAdapter = ArrayAdapter.createFromResource(this, R.array.Results, android.R.layout.simple_spinner_item);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultSpinner.setAdapter(resultAdapter);
        setResultSpinner(dbResult);

        returnToList = new Intent();
    }

    public void goToList(View view){
        finish();
    }

    public void updateClick(View view){
        //before we go to this we should run the full check on if all the fields contain a valid selection
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
            //I believe we are going to remove this and change the confirmation dialog to the delete entry option
            //confirmationDialog();
            updateInformation();
        }
    }

    public void confirmationDialog(){
        //alert dialog information found on Android Documentation with additional help being taken from Stack Overflow
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

    public void setPlayLevel(String playLevelFromDB){
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

    public void setResultSpinner(String resultFromDB){
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

    public void updateInformation(){
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

    public void inflateObjects(){
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

    public void deleteDBEntry(){
        //if I did this wrong it will delete the whole database
        db.delete(DB_TableName,"_id=" + Integer.toString(positionInDB), null);
        setResult(Activity.RESULT_OK, returnToList);
        finish();

    }

    public void deleteMatch(View view){
        confirmationDialog();
    }
}
