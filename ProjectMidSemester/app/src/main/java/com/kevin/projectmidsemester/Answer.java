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
    TextView thisIsTest;
    Intent backToQuestion;
    private String optionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerscreen1);

        thisIsTest = (TextView) findViewById(R.id.textView6);
        numTries = getIntent().getIntExtra("Number of Tries",0);
        optionSelected = getIntent().getStringExtra("Option Selected");
        thisIsTest.setText(optionSelected);
        backToQuestion = new Intent();
    }

    public void tryAgain(View view){
        Log.d("Brenneman","going back to asking questions");
        numTries--;
        backToQuestion.putExtra("New Number of Tries",numTries);
        setResult(Activity.RESULT_OK,backToQuestion);
        finish();
    }
}
