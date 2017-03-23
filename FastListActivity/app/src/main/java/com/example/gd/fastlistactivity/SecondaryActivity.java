package com.example.gd.fastlistactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by GD on 3/22/2017.
 */

public class SecondaryActivity extends AppCompatActivity {
    String[] ourMajors = {"Computer Engineering","Computer Science", "Software Engineering"};
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_main); //regular screen with listview
        ArrayAdapter<String> myAddapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,ourMajors);
        myListView = (ListView)findViewById(R.id.listview2);
        myListView.setAdapter(myAddapter);
        //setListAdapter(new ArrayAdapter<String>(this,R.layout.listitem,R.id.listitem));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//creates the menu in actionbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
