package com.example.gd.scifi;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements MyButtonFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onWebSiteItemSelected(String link) {
       STrekNGFragment strekFragment = (STrekNGFragment) getFragmentManager().findFragmentById(R.id.fragmentwebpage);
        if (strekFragment != null && strekFragment.isInLayout()){
            strekFragment.setWebPage(link);
        }
        else{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        }

    }
}
