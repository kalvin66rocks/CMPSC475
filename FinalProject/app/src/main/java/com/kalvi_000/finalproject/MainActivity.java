package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] menuChoices = {"Enter a Match","View Match History", "Clear All Match History", "Look up a Card"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,R.id.menuOption, menuChoices));
    }
}
