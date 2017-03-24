package com.kevin.projectmidsemester;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    ImageView imageView;
    Drawable draw;
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
        imageView = (ImageView) findViewById(R.id.imageView2);
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
                //properly draw the image here
                imageView.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.survivalofthefittest);
                imageView.setImageDrawable(draw);
                break;
            //limited
            case 1:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.standard_answer);
                //properly draw the image here
                imageView.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.limited);
                imageView.setImageDrawable(draw);
                break;
            //standard
            case 2:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.standard_answer);
                //properly draw the image here
                imageView.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.heartofkiran);
                imageView.setImageDrawable(draw);
                break;
            //modern
            case 3:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.modern_answer);
                //properly draw the image here
                imageView.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.tarmogoyf);
                imageView.setImageDrawable(draw);
                break;
            //legacy
            case 4:
                result.setText(notQuite);
                numTries--;
                textViewInfo.setText(R.string.legacy_answer);
                //properly draw the image here
                imageView.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.brainstorm);
                imageView.setImageDrawable(draw);
                break;
            //vintage
            case 5:
                result.setText(correct);
                textViewInfo.setText(R.string.vintage_answer);
                //properly draw the image here
                imageView.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ancestral_recall);
                imageView.setImageDrawable(draw);
                break;

            default:
                //do nothing. we will never be here
                break;
        }
    }
}
