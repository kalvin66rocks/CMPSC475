package com.example.g2d.favoritecity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] attraction = {"Alcatraz Island", "Ferry Marketplace", "Golden Gate Bridge",
                "Cable Car Trolley", "Fisherman's Wharf"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, R.id.travel, attraction));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://alcatrazcruises.com/")));
                break;
            case 1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ferrybuildingmarketplace.com")));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, Bridge.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, Trolley.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, Wharf.class));
                break;
        }
    }
}
