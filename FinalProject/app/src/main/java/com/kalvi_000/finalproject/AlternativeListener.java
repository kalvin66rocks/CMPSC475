package com.kalvi_000.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kevin on 4/26/2017.
 * This was listener created in an alternative attempt to get messages from wear to mobile
 */

public class AlternativeListener extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        if (intent.getType().equals("text/plain")) {
            String extra = intent.getStringExtra(Intent.EXTRA_TEXT);
            Log.d("OnMessageRevieved *****", extra);
            Toast.makeText(this, "We recieved text!" ,Toast.LENGTH_SHORT).show();
        }
    }

}
