package com.kevin.projectmidsemester;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by kalvi_000 on 3/11/2017.
 */

public class History extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        //properly draw the image here
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.magicthegatheringlogo);
        imageView.setImageDrawable(draw);
    }
}
