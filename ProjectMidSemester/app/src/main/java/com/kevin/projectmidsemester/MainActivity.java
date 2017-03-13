package com.kevin.projectmidsemester;

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
        String[] coolFacts = {"How did magic begin?", "Magic TCG Reddit Page", "Cool Fact 3",
                "Cool Fact 4", "Cool Fact 5"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, R.id.coolFact, coolFacts));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://gatherer.wizards.com/Pages/Default.aspx")));
                startActivity(new Intent(MainActivity.this, History.class));
                break;
            case 1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.reddit.com/r/magictcg")));
                break;
            case 2:
                //startActivity(new Intent(MainActivity.this, Bridge.class));
                break;
            case 3:
                //startActivity(new Intent(MainActivity.this, Trolley.class));
                break;
            case 4:
                //startActivity(new Intent(MainActivity.this, Wharf.class));
                break;
        }
    }
}
