package com.kevin.projectmidsemester;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Kevin on 3/15/2017.
 */

public class Question extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionscreen1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutSSS);
        switch (id){
            case R.id.action_settings2:
                Toast.makeText(this, "Blue", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.action_settings3:
                Toast.makeText(this, "Yellow", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.action_settings4:
                Toast.makeText(this, "Red", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.RED);
                return true;
            case R.id.action_settings7:
                Toast.makeText(this, "2 tries, tries reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings8:
                Toast.makeText(this, "3 tries, tries reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings9:
                Toast.makeText(this, "4 tries, tries reset", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToMain(View view){
        finish();
    }
}
