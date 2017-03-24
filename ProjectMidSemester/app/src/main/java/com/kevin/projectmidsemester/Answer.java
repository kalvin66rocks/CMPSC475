package com.kevin.projectmidsemester;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by kalvi_000 on 3/23/2017.
 */

public class Answer extends Activity {
    int numTries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionscreen1);
    }
}
