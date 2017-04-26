package com.kalvi_000.finalproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Kevin on 4/23/2017.
 */

public class ScrollActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_layout);

        //connect all views in onCreate
        connectViews();
    }

    private void connectViews(){

    }

    //will probably have a button to alter the contents of a scroll view.
    //perhaps radial buttons or other buttons to select my favorites cards of each color.
    //then update the scroll view of pictures to have that set of stuff
    //maybe this will be an active listener that does not require a button to be pressed
    // maybe a spinner would work well as well
}
