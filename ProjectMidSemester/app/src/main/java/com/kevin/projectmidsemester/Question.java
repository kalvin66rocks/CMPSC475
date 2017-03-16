package com.kevin.projectmidsemester;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public void goToMain(View view){
        finish();
    }
}
