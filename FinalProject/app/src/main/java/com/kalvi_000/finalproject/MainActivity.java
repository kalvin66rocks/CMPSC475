package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends ListActivity implements  DatabaseConstants{

    //stuff that we are going to use temporarily seeing if our database functions
    // using logcat
    //database stuff
    SQLiteDatabase db;
    Cursor cursor;
    private EventsData events;
    int dbID, dbPlayLevel;
    String dbName, dbDeckPlayed, dbOpponent, dbOpponentDeck, dbResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] menuChoices = {"Enter a Match","View Match History", "Clear All Match History", "Look up a Card"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,R.id.menuOption, menuChoices));

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo webShortcut = new ShortcutInfo.Builder(this, "shortcut_web")
                .setShortLabel("Card Search")
                .setLongLabel("Open wizard's website to search for cards")
                .setIcon(Icon.createWithResource(this, R.drawable.planeswalker_symbol))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://gatherer.wizards.com/Pages/Default.aspx")))
                .build();

        shortcutManager.setDynamicShortcuts(Collections.singletonList(webShortcut));

        ShortcutInfo dynamicShortcut = new ShortcutInfo.Builder(this, "shortcut_dynamic")
                .setShortLabel("Enter a Match Result")
                .setLongLabel("Enter the Results of a Match Quickly")
                .setIcon(Icon.createWithResource(this, R.drawable.planeswalker_symbol))
                .setIntents(
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                                new Intent(EnterMatches.ACTION)
                        })
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(webShortcut, dynamicShortcut));

        //database stuff
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, EnterMatches.class));
                break;
            case 1:
                Toast.makeText(this, "View Matches", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(MainActivity.this, HowToPlay.class));
                //this will be view match history.
                //will be temporarily used to debug db via logcat
                cursor = db.query(DB_TableName, null, null, null, null, null, null);
                //cursor.moveToFirst();
                //no idea what the fuck is going on here
                //start the cursor at the beginning of the table
                while (cursor.moveToNext()) {  //move to next row, if possible
                    Toast.makeText(this, "Made it inside while loop", Toast.LENGTH_SHORT).show();
                    dbID = cursor.getInt(0);
                    Log.d("Query***** ID:", Integer.toString(dbID));
                    dbPlayLevel = cursor.getInt(1);
                    Log.d("Query***** PlayLevel:", Integer.toString(dbPlayLevel));
                    dbName =cursor.getString(2);
                    Log.d("Query*****", dbName);
                    dbDeckPlayed = cursor.getString(3);
                    Log.d("Query*****", dbDeckPlayed);
                    dbOpponent = cursor.getString(4);
                    Log.d("Query*****", dbOpponent);
                    dbOpponentDeck = cursor.getString(5);
                    Log.d("Query*****", dbOpponentDeck);
                    dbResult = cursor.getString(6);
                    Log.d("Query*****", dbResult);

                    //will try launching a new activity from here, I think it should be a list activity

                }
                break;
            case 2:
                //startActivity(new Intent(MainActivity.this, Question.class));
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
