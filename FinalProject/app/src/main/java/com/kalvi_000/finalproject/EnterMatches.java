package com.kalvi_000.finalproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by kalvi_000 on 4/11/2017.
 */

public class EnterMatches extends Activity
{

    public static final String ACTION = BuildConfig.APPLICATION_ID + ".OPEN_DYNAMIC_SHORTCUT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entermatch);
    }
}
