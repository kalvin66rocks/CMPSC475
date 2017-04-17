package com.kevin.projectmidsemester;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by kalvi_000 on 3/12/2017.
 */

//note to self. Declare all classes in the android manifest or the app will crash

public class HowToPlay extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);

        //properly draw the image here
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.howtoplay);
        imageView.setImageDrawable(draw);
    }

    public void channelFireballHowTo(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.channelfireball.com/articles/how-to-play-magic-the-gathering/")));
        overridePendingTransition(R.anim.swipe_up,R.anim.swipe_up);
    }
    public void wizardsHowTo(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://magic.wizards.com/en/gameplay/how-to-play")));
        overridePendingTransition(R.anim.blink,R.anim.blink);
    }

    public void goToMain(View view){
        finish();
    }
}
