package com.kalvi_000.fragmentationproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by kalvi_000 on 4/6/2017.
 */

public class Answer extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_answer_frag);
        Intent intent = getIntent();
        int answerSelected = intent.getIntExtra("TheSelection", 0);
        Log.d("Brenneman", Integer.toString(answerSelected));
        TextView answerBox = (TextView)findViewById(R.id.textView2);
        switch(answerSelected){
            case 0:
                answerBox.setText(R.string.standard_answer);
                break;
            case 1:
                answerBox.setText(R.string.modern_answer);
                break;
            case 2:
                answerBox.setText(R.string.legacy_answer);
                break;
            case 3:
                answerBox.setText(R.string.vintage_answer);
                break;
            case 4:
                answerBox.setText(R.string.extended_answer);
                break;
        }

    }
}
