package com.kevin.projectmidsemester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kalvi_000 on 3/23/2017.
 */

public class Answer extends Activity {
    private int numTries=0;
    //TextView answerInformation;
    TextView result;
    TextView textViewInfo;
    Intent backToQuestion;
    private int optionSelected;
    private String notQuite = "Not Quite";
    private String correct = "Correct!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerscreen1);

        //answerInformation = (TextView) findViewById(R.id.textView6);
        result = (TextView) findViewById(R.id.textViewResult);
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);
        numTries = getIntent().getIntExtra("Number of Tries",0);
        optionSelected = getIntent().getIntExtra("Option Selected",0);
        changeContent(optionSelected);
        //answerInformation.setText(Integer.toString(optionSelected));
        backToQuestion = new Intent();
    }

    public void tryAgain(View view){
        Log.d("Brenneman","going back to asking questions");
        backToQuestion.putExtra("New Number of Tries",numTries);
        setResult(Activity.RESULT_OK,backToQuestion);
        finish();
    }

    public void changeContent(int selected){

        switch (selected){
            //extended
            case 0:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.standard_answer);
                //do stuff
                break;
            //limited
            case 1:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.standard_answer);
                //do stuff
                break;
            //standard
            case 2:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.standard_answer);
                //do stuff
                break;
            //modern
            case 3:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.modern_answer);
                //do stuff
                break;
            //legacy
            case 4:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.legacy_answer);
                //do stuff
                break;
            //vintage
            case 5:
                result.setText(correct);
                break;

            default:
                //do nothing. we will never be here
                break;
        }
    }
}
