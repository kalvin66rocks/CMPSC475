package com.kevin.projectmidsemester;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private TextView numTries;
    private Intent callAnswer;

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
                Toast.makeText(this, "tries reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings8:
                numTries.setText("3");
                Toast.makeText(this, "tries reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings9:
                numTries.setText("4");
                Toast.makeText(this, "tries reset", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void legacy(View view){
        Log.d("Brenneman","legacy selected");
        //if statement


    }

    public void modern(View view){
        Log.d("Brenneman","modern selected");
        //if statement

    }

    public void limited(View view){
        Log.d("Brenneman","limited selected");
        //if statement

    }

    public void standard(View view){
        Log.d("Brenneman","standard selected");
        //if statement

    }

    public void vintage(View view){
        Log.d("Brenneman","vintage selected");
        //if statement

    }

    public void extended(View view){
        Log.d("Brenneman","extended selected");
        //if statement

    }

    public void goToMain(View view){
        finish();
    }
}
