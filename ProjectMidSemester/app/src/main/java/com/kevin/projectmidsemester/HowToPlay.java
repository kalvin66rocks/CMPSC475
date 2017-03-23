package com.kevin.projectmidsemester;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by kalvi_000 on 3/12/2017.
 */

//note to self. Declare all classes in the android manifest or the app will crash

public class HowToPlay extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);
    }

    public void channelFireballHowTo(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.channelfireball.com/articles/how-to-play-magic-the-gathering/")));
        overridePendingTransition(R.anim.link1_in,R.anim.link1_out);
    }
    public void wizardsHowTo(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://magic.wizards.com/en/gameplay/how-to-play")));
        overridePendingTransition(R.anim.link1_in,R.anim.link1_out);
    }

    public void goToMain(View view){
        finish();
    }
}
