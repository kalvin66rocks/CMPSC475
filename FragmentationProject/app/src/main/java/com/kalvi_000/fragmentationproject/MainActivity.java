package com.kalvi_000.fragmentationproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements selectAnswerFrag.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAnswerItemSelected(int positiveNumber) {
        Log.d("Brenneman", Integer.toString(positiveNumber));
        showAnswerFrag showDaAnswer = (showAnswerFrag) getFragmentManager().findFragmentById(R.id.showAnswerFragLayout);
        TextView answerViewe = (TextView)findViewById(R.id.textView2);



    }
}
