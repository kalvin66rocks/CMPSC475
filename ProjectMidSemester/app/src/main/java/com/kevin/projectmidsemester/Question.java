package com.kevin.projectmidsemester;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;

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

    public void goToMain(View view){
        finish();
    }
}
