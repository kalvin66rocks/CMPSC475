package com.kevin.projectmidsemester;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by kalvi_000 on 3/12/2017.
 */

//note to self. Declare all classes in the android manifest or the app will crash

public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    public void goToMain(View view){
        finish();
    }
}
