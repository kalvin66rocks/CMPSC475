package com.kalvi_000.fragmentationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView answerView = (TextView)findViewById(R.id.textView2);

        if(showDaAnswer != null && showDaAnswer.isInLayout()){
            Log.d("Brenneman","Fragment stuff");
            showDaAnswer.setAnswerText(positiveNumber, answerView);
        }
        else{
            //Toast.makeText(this, "Why am I here", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Answer.class);
            intent.putExtra("TheSelection",positiveNumber);
            startActivity(intent);
        }



    }
}
