package com.kalvi_000.finalproject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Kevin on 4/23/2017.
 */

public class ScrollActivity extends Activity {

    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;

    Drawable draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_layout);

        //connect all views in onCreate
        connectViews();
    }

    private void connectViews(){
        //because images are stupid, do these steps to make images properly viewable
        card1 = (ImageView) findViewById(R.id.imageView);
        card1.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.brainstorm);
        card1.setImageDrawable(draw);

        card2 = (ImageView) findViewById(R.id.imageView2);
        card2.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ancestral_recall);
        card2.setImageDrawable(draw);

        card3 = (ImageView) findViewById(R.id.imageView3);
        card3.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.survivalofthefittest);
        card3.setImageDrawable(draw);

    }

    //will probably have a button to alter the contents of a scroll view.
    //perhaps radial buttons or other buttons to select my favorites cards of each color.
    //then update the scroll view of pictures to have that set of stuff
    //maybe this will be an active listener that does not require a button to be pressed
    // maybe a spinner would work well as well
}
