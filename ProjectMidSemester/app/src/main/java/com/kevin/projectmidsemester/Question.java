package com.kevin.projectmidsemester;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin on 3/15/2017.
 */

public class Question extends Activity {
    public static final int REQUEST_CODE = 10;
    private TextView numTries;
    private Intent callAnswer;
    private int numberTries;
    private int numTriesRemaining;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionscreen1);

        numTries = (TextView) findViewById(R.id.remainingTries);
        callAnswer = new Intent(this, Answer.class);
    }
    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //meun handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutSSS);
        switch (id){
            case R.id.action_settings2:
                //Toast.makeText(this, "Blue", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.action_settings3:
                //Toast.makeText(this, "Yellow", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.action_settings4:
                //Toast.makeText(this, "Red", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.RED);
                return true;
            case R.id.action_settings7:
                numTries.setText("2");
                Toast.makeText(this, "Tries Reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings8:
                numTries.setText("3");
                Toast.makeText(this, "Tries Reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings9:
                numTries.setText("4");
                Toast.makeText(this, "Tries Reset", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void legacy(View view){
        Log.d("Brenneman","legacy selected");
        numTriesRemaining = Integer.parseInt(numTries.getText().toString());
        if(numTriesRemaining >0) {
            callAnswer.putExtra("Number of Tries", numTriesRemaining);
            callAnswer.putExtra("Option Selected", 4);
            startActivityForResult(callAnswer, REQUEST_CODE);
        }
        else{
            Toast.makeText(this, "No tries remaining", Toast.LENGTH_LONG).show();
        }
    }

    public void modern(View view){
        Log.d("Brenneman","modern selected");
        numTriesRemaining = Integer.parseInt(numTries.getText().toString());
        if(numTriesRemaining >0) {
            callAnswer.putExtra("Number of Tries", numTriesRemaining);
            callAnswer.putExtra("Option Selected", 3);
            startActivityForResult(callAnswer, REQUEST_CODE);
        }
        else{
            Toast.makeText(this, "No tries remaining", Toast.LENGTH_LONG).show();
        }

    }

    public void limited(View view){
        Log.d("Brenneman","limited selected");
        numTriesRemaining = Integer.parseInt(numTries.getText().toString());
        if(numTriesRemaining >0) {
            callAnswer.putExtra("Number of Tries", numTriesRemaining);
            callAnswer.putExtra("Option Selected", 1);
            startActivityForResult(callAnswer, REQUEST_CODE);
        }
        else{
            Toast.makeText(this, "No tries remaining", Toast.LENGTH_LONG).show();
        }

    }

    public void standard(View view){
        Log.d("Brenneman","standard selected");
        numTriesRemaining = Integer.parseInt(numTries.getText().toString());
        if(numTriesRemaining >0) {
            callAnswer.putExtra("Number of Tries", numTriesRemaining);
            callAnswer.putExtra("Option Selected", 2);
            startActivityForResult(callAnswer, REQUEST_CODE);
        }
        else{
            Toast.makeText(this, "No tries remaining", Toast.LENGTH_LONG).show();
        }

    }

    public void vintage(View view){
        Log.d("Brenneman","vintage selected");
        numTriesRemaining = Integer.parseInt(numTries.getText().toString());
        if(numTriesRemaining >0) {
            callAnswer.putExtra("Number of Tries", numTriesRemaining);
            callAnswer.putExtra("Option Selected", 5);
            startActivityForResult(callAnswer, REQUEST_CODE);
        }
        else{
            Toast.makeText(this, "No tries remaining", Toast.LENGTH_LONG).show();
        }

    }

    public void extended(View view){
        Log.d("Brenneman","extended selected");
        numTriesRemaining = Integer.parseInt(numTries.getText().toString());
        if(numTriesRemaining >0) {
            callAnswer.putExtra("Number of Tries", numTriesRemaining);
            callAnswer.putExtra("Option Selected", 0);
            startActivityForResult(callAnswer, REQUEST_CODE);
        }
        else{
            Toast.makeText(this, "No tries remaining", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Brenneman","Entering onActivityResult");
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                numberTries = data.getIntExtra("New Number of Tries", 0);
                numTries.setText(Integer.toString(numberTries));
            }
            else{
                Log.d("Brenneman","Something cancelled the call from the answer");
                Toast.makeText(this, "Please don't use the back arrow. Use the Back button on the screen", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void goToMain(View view){
        finish();
    }
}
