package com.kalvi_000.intenttesting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.support.v7.appcompat.R.styleable.View;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE = 10;
    int mainNum=5;
    TextView mainTextView;
    Intent callSecondary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            Log.d("Brenneman","saved instance");
            mainNum=savedInstanceState.getInt("CurrentNumber");
        }
        setContentView(R.layout.activity_main);

        mainTextView = (TextView) findViewById(R.id.textView);
        callSecondary = new Intent(this, SecondaryActivity.class);
    }

    public void OnClickCallSecond(View v){
        Log.d("Brenneman","Entering OnClickCallSecond");
        callSecondary.putExtra("Value from Main",mainNum);
        startActivityForResult(callSecondary,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Brenneman","Entering onActivityResult");
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                mainNum= data.getIntExtra("The Changed Number",0);
                mainTextView.setText(Integer.toString(mainNum));
            }
            else{
                Log.d("Brenneman","Something cancelled the second call");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("CurrentNumber",mainNum);
        super.onSaveInstanceState(savedInstanceState);
    }
}
