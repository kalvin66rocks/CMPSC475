package com.kalvi_000.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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
 */

public class EnterMatches
        extends Activity
        implements View.OnLongClickListener,
        CompoundButton.OnCheckedChangeListener,
        DatabaseConstants
{

    Button commitData;
    RadioButton casualRB,testingRB,competitiveRB;
    EditText name,opponent,deckPlayed,opponentDeck;
    Spinner resultSpinner;
    private int playLevel = 0;

    //database stuff
    SQLiteDatabase db;
    Cursor cursor;
    private EventsData events;

    //used for creating the relationship between this activity
    //and the shortcut that is created from the home screen.
    public static final String ACTION = BuildConfig.APPLICATION_ID + ".OPEN_DYNAMIC_SHORTCUT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entermatch);

        //connect the button the can commit and clear data
        commitData = (Button) findViewById(R.id.EnterButtonEnterConent);
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
        name = (EditText) findViewById(R.id.name);
        deckPlayed = (EditText) findViewById(R.id.deckPlayed);
        opponent = (EditText) findViewById(R.id.opponent);
        opponentDeck = (EditText) findViewById(R.id.opponentDeck);

        //connect the result spinner
        //also populate it with its options
        resultSpinner = (Spinner) findViewById(R.id.ResultSpinner);
        ArrayAdapter<CharSequence> resultAdapter = ArrayAdapter.createFromResource(this, R.array.Results, android.R.layout.simple_spinner_item);
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultSpinner.setAdapter(resultAdapter);

        //database stuff
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database


    }

    public void commitResults(View view){
        boolean incomplete = false;
        if (playLevel == 0){
            Toast.makeText(this, "Please select a level of play", Toast.LENGTH_SHORT).show();
            incomplete = true;
        }
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


        //after all data is verified we will enter the result into the database
        if(!incomplete){
            Toast.makeText(this, "valid options, push entry to database", Toast.LENGTH_SHORT).show();
            //we have to build the items to push to the database here
            ContentValues someValues = new ContentValues(); // this is a single row in the database.
            //no idea how these will behave, will have to comment out advance features and run at ap level 21 for now
            someValues.put("name", name.getText().toString());
            someValues.put("deckPlayed", deckPlayed.getText().toString());
            someValues.put("opponent", opponent.getText().toString());
            someValues.put("name", name.getText().toString());
            db.insert(DB_TableName, null, someValues);
            clearFields();
        }else if(incomplete){
            Toast.makeText(this, "Something is invalid, would not proceed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.casualRadioButton:
                if(isChecked){
                    playLevel=1;
                }
                break;
            case R.id.testingRadioButton:
                if(isChecked){
                    playLevel=2;
                }
                break;
            case R.id.competitiveRadioButton:
                if(isChecked){
                    playLevel=3;
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



    public void clearFields(){
        //uncheck the radio buttons
        casualRB.setChecked(false);
        testingRB.setChecked(false);
        competitiveRB.setChecked(false);

        //clear the text field
        name.setText("");
        deckPlayed.setText("");
        opponent.setText("");
        opponentDeck.setText("");

        //need to clear the selection on the spinner
    }


    public void goToMain(View view){
        finish();
    }
}
