package com.kalvi_000.intenttesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kalvi_000 on 3/2/2017.
 */

public class SecondaryActivity extends AppCompatActivity {

    TextView secondaryTextView;
    int secondaryNum;
    Intent callMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        secondaryTextView = (TextView) findViewById(R.id.textView2);
        secondaryNum = getIntent().getIntExtra("Value from Main",0);
        secondaryTextView.setText(Integer.toString(secondaryNum));
        //callMain = new Intent(new Intent(this,MainActivity.class));
        callMain = new Intent();    //EMPTY INTENT
    }
    public void OnClickCallMain(View v){
        Log.d("Brenneman","Entering OnClickCallMain");
        secondaryNum *=5;
        callMain.putExtra("The Changed Number",secondaryNum);
        setResult(Activity.RESULT_OK,callMain);
        finish();
    }
}
