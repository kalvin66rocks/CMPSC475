package com.kalvi_000.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by kalvi_000 on 4/11/2017.
 */

public class EnterMatches extends Activity implements View.OnLongClickListener,CompoundButton.OnCheckedChangeListener
{

    Button commitData;
    RadioButton casualRB,testingRB,competitiveRB;
    EditText name,opponent,deckPlayed,opponentDeck;
    private int playLevel = 0;

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
    }

    public void commitResults(View view){
        boolean incomplete = false;
        if (playLevel == 0){
            Toast.makeText(this, "Please select a level of play", Toast.LENGTH_SHORT).show();
            incomplete = true;
        }
        //will display error message on no name entered
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


        //after all data is verified we will enter the result into the database
        if(!incomplete){
            Toast.makeText(this, "valid options, would proceed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.casualRadioButton:
                if(isChecked){
                    Toast.makeText(this, "Casual radio button value set", Toast.LENGTH_SHORT).show();
                    playLevel=1;
                }
                break;
            case R.id.testingRadioButton:
                if(isChecked){
                    Toast.makeText(this, "Testing radio button value set", Toast.LENGTH_SHORT).show();
                    playLevel=2;
                }
                break;
            case R.id.competitiveRadioButton:
                if(isChecked){
                    Toast.makeText(this, "Competitive radio button value set", Toast.LENGTH_SHORT).show();
                    playLevel=2;
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

        //more to come on clearing the text fields
    }


    public void goToMain(View view){
        finish();
    }
}
