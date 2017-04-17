package com.example.gd.startrekactors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements MyActorSelFrag.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onActorItemSelected(int posit) {

        Log.d("DudasX", Integer.toString(posit));
        ShowActorFrag daShowFragment = (ShowActorFrag) getFragmentManager().findFragmentById(R.id.actors_show_frag);
        ImageView iv= (ImageView)findViewById(R.id.imageView);
        if (daShowFragment != null && daShowFragment.isInLayout() ){
       // if (daShowFragment !=null){
            Log.d("Dude--","m");
            daShowFragment.setPicture(posit, iv);
        }else{
            Intent intent = new Intent(this, ActorsActivity.class);
            intent.putExtra("TheSelection", posit);
            startActivity(intent);
        }
    }
}
