package com.example.gd.startrekactors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by GD on 3/27/2017.
 */

public class ActorsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actor_picture_frag);
        Intent intent = getIntent();
        int pictureDesired = intent.getIntExtra("TheSelection", 0);
        Log.d("Dudas",Integer.toString(pictureDesired));
        ImageView iv= (ImageView)findViewById(R.id.imageView);
        switch (pictureDesired){
            case 0:
                iv.setImageResource(R.drawable.spock);
                break;
            case 1:
                iv.setImageResource(R.drawable.kirk);
                break;
            case 2:
                iv.setImageResource(R.drawable.scotty);
                break;
        }


    }
}
