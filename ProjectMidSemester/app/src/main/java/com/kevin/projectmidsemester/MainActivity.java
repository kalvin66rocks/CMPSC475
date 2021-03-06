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
        String[] coolFacts = {"How did magic begin?", "How do I play?", "What about organized magic?",
                "Empty", "Empty"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, R.id.coolFact, coolFacts));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, History.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, HowToPlay.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, Question.class));
                break;
            case 3:
                //empty
                break;
            case 4:
                //empty
                break;
        }
    }
}
