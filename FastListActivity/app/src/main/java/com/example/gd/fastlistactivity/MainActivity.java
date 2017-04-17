package com.example.gd.fastlistactivity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] ourMajors = {"Computer Engineering","Computer Science", "Software Engineering"};
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ourMajors));
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,R.id.thetextpart,ourMajors));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(MainActivity.this,SecondaryActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
