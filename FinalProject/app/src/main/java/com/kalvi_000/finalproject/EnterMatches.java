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

public class EnterMatches
        extends Activity
        implements View.OnLongClickListener,
        CompoundButton.OnCheckedChangeListener,
        DatabaseConstants
{

    private Button commitData;
    private RadioButton casualRB,testingRB,competitiveRB;
    private EditText name,opponent,deckPlayed,opponentDeck;
    private Spinner resultSpinner;
    private String playLevel = "";

    //database stuff
    private SQLiteDatabase db;
    //Cursor cursor;
    //private EventsData events;

    //used for creating the relationship between this activity
    //and the shortcut that is created from the home screen.
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
        boolean fieldBlank = false;
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
        if(!fieldBlank){
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
        }else //noinspection ConstantConditions
            if(fieldBlank){
            Toast.makeText(this, "Something is invalid, would not proceed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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

    //will only be attacking this to the enter/clear button
    @Override
    public boolean onLongClick(View arg0) {
        clearFields();
        Toast.makeText(this, "All Fields Cleared", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void connectStuff(){
        //connect the button the can commit and clear data
        commitData = (Button) findViewById(R.id.EnterButtonEnterContent);
        commitData.setOnLongClickListener(this);

        //connect radial button
        casualRB = (RadioButton) findViewById(R.id.casualRadioButton);
        casualRB.setOnCheckedChangeListener(this);
        //connect radial button
        testingRB = (RadioButton) findViewById(R.id.testingRadioButton);
        testingRB.setOnCheckedChangeListener(this);
        //connect radial button
        competitiveRB = (RadioButton) findViewById(R.id.competitiveRadioButton);
        competitiveRB.setOnCheckedChangeListener(this);

        //connect the edit Texts
        name = (EditText) findViewById(R.id.nameEdit);
        deckPlayed = (EditText) findViewById(R.id.deckPlayedEdit);
        opponent = (EditText) findViewById(R.id.opponentEdit);
        opponentDeck = (EditText) findViewById(R.id.opponentDeckEdit);

        //connect the result spinner
        //also populate it with its options
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

        playLevel = "";

        //need to clear the selection on the spinner
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

    //restore data
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
