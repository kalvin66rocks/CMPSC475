package com.kalvi_000.finalproject;

import android.app.Activity;
import android.content.ContentValues;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by kalvi_000 on 4/11/2017.
 * Enter match information here
 */

public class EnterMatches extends Activity implements View.OnLongClickListener, CompoundButton.OnCheckedChangeListener, DatabaseConstants
{

    //connect UI elements
    private Button commitData;
    private RadioButton casualRB,testingRB,competitiveRB;
    private EditText name,opponent,deckPlayed,opponentDeck;
    private Spinner resultSpinner;

    //string used for keeping track of the option selected in the checkbox
    private String playLevel = "";

    //database stuff
    private SQLiteDatabase db;

    //this string is used by the dynamic shortcut manager for launching this activity
    public static final String ACTION = BuildConfig.APPLICATION_ID + ".OPEN_DYNAMIC_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entermatch);

        connectStuff();

        //database stuff
        EventsData events;
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database


    }

    public void commitResults(@SuppressWarnings("UnusedParameters") View view){
        //keep track of  if a field has been left blank
        boolean fieldBlank = false;

        //if the user doesn't have a radial box selected
        if (playLevel.equals("")){
            Toast.makeText(this, "Please select a level of play", Toast.LENGTH_SHORT).show();
            fieldBlank = true;
        }

        //will display error messages if any of the fields have been left empty
        String nameString = name.getText().toString();
        if(TextUtils.isEmpty(nameString)){
            name.setError("Name cannot be empty");
            fieldBlank = true;
        }
        String deckString = deckPlayed.getText().toString();
        if(TextUtils.isEmpty(deckString)){
            deckPlayed.setError("Deck cannot be empty");
            fieldBlank = true;
        }
        String opponentString = opponent.getText().toString();
        if(TextUtils.isEmpty(opponentString)){
            opponent.setError("Opponent cannot be empty");
            fieldBlank = true;
        }
        String opponentDeckString = opponentDeck.getText().toString();
        if(TextUtils.isEmpty(opponentDeckString)){
            opponentDeck.setError("Opponent's Deck cannot be empty");
            fieldBlank = true;
        }
        if(!casualRB.isChecked() && !testingRB.isChecked() && !competitiveRB.isChecked()){
            fieldBlank = true;
        }

        //after all data is verified we will enter the result into the database
        if(!fieldBlank) {
            //we have to build the items to push to the database here
            ContentValues someValues = new ContentValues(); // this is a single row in the database.
            someValues.put("playlevel", playLevel);
            someValues.put("name", name.getText().toString());
            someValues.put("deckplayed", deckPlayed.getText().toString());
            someValues.put("opponent", opponent.getText().toString());
            someValues.put("opponentdeck", opponentDeck.getText().toString());
            someValues.put("result", resultSpinner.getSelectedItem().toString());
            db.insert(DB_TableName, null, someValues);
            clearFields();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //this will all trigger whenever a radial button is selected
        //this will also set the playlevel
        switch (buttonView.getId()) {
            case R.id.casualRadioButton:
                if(isChecked){
                    playLevel="Casual";
                }
                break;
            case R.id.testingRadioButton:
                if(isChecked){
                    playLevel="Testing";
                }
                break;
            case R.id.competitiveRadioButton:
                if(isChecked){
                    playLevel="Competitive";
                }
                break;

        }
    }

    //allows the user to clear all the fields by long pressing the enter button
    @Override
    public boolean onLongClick(View arg0) {
        clearFields();
        Toast.makeText(this, "All Fields Cleared", Toast.LENGTH_SHORT).show();
        return true;
    }

    //turned connecting everything into a function to make everything cleaner
    private void connectStuff(){
        //connect the button the can commit and clear data
        commitData = (Button) findViewById(R.id.EnterButtonEnterContent);
        commitData.setOnLongClickListener(this);

        //connect radial buttons
        casualRB = (RadioButton) findViewById(R.id.casualRadioButton);
        casualRB.setOnCheckedChangeListener(this);

        testingRB = (RadioButton) findViewById(R.id.testingRadioButton);
        testingRB.setOnCheckedChangeListener(this);

        competitiveRB = (RadioButton) findViewById(R.id.competitiveRadioButton);
        competitiveRB.setOnCheckedChangeListener(this);

        //connect the edit Texts
        name = (EditText) findViewById(R.id.nameEdit);
        deckPlayed = (EditText) findViewById(R.id.deckPlayedEdit);
        opponent = (EditText) findViewById(R.id.opponentEdit);
        opponentDeck = (EditText) findViewById(R.id.opponentDeckEdit);

        //connect the result spinner and populate it from array
        resultSpinner = (Spinner) findViewById(R.id.ResultSpinner);
        ArrayAdapter<CharSequence> resultAdapter = ArrayAdapter.createFromResource(this, R.array.Results, android.R.layout.simple_spinner_item);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultSpinner.setAdapter(resultAdapter);
    }

    private void clearFields(){
        //uncheck the radio buttons
        casualRB.setChecked(false);
        testingRB.setChecked(false);
        competitiveRB.setChecked(false);

        //clear the text field
        name.setText("");
        deckPlayed.setText("");
        opponent.setText("");
        opponentDeck.setText("");

        //clear playLevel
        playLevel = "";

        //need to clear the selection on the spinner
        resultSpinner.setSelection(0);
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

    //restore data on phone rotation
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

    public void goToMain(@SuppressWarnings("UnusedParameters") View view){
        finish();
    }
}
