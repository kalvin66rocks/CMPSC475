package com.kalvi_000.finalproject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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

    //database stuff
    SQLiteDatabase db;
    Cursor cursor;
    private EventsData events;
    int dbID;
    String dbName, dbDeckPlayed, dbOpponent, dbOpponentDeck, dbResult, dbPlayLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] menuChoices = {"Enter a Match","View Match History", "Clear All Match History", "Look up a Card", "TBD"};
        setListAdapter(new ArrayAdapter<>(this, R.layout.activity_main,R.id.menuOption, menuChoices));

        //function call to set dynamic shortcuts
        createShortCuts();

        //set up some database stuff
        //only needed on this screen for deleting the entire database.
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, EnterMatches.class));
                break;
            case 1:
                //Toast.makeText(this, "View Matches", Toast.LENGTH_SHORT).show();
                //this will be view match history.
                //will be temporarily used to debug db via logcat
                cursor = db.query(DB_TableName, null, null, null, null, null, null);
                while (cursor.moveToNext()) {  //move to next row, if possible
                    dbID = cursor.getInt(0);
                    Log.d("Query***** ID:", Integer.toString(dbID));
                    dbPlayLevel = cursor.getString(1);
                    Log.d("Query***** PlayLevel:", dbPlayLevel);
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
                }
                startActivity(new Intent(MainActivity.this, ViewMatches.class));
                break;
            case 2:
                //delete all entries after 2 confirmations
                deleteDialog();
                break;
            case 3:
                //allows the user to go out to a website and search cards
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://gatherer.wizards.com/Pages/Default.aspx")));
                break;
            case 4:
                //not sure what this will be used for but I imagine it will be something to do with a webview activity
                //empty
                break;
        }
    }

    public void createShortCuts(){
        //Dynamic Shortcut instructions found at https://catinean.com/2016/10/20/exploring-android-nougat-7-1-app-shortcuts/
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
    }

    public void deleteDialog(){
        //alert dialog information found on Android Documentation with additional help being taken from Stack Overflow
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("This will delete all matches. Are you sure?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        confirmDeleteDialog(); //this needs to be a function call
                    }})
                .setNegativeButton("No", null).show();

    }

    public void confirmDeleteDialog(){
        //alert dialog information found on Android Documentation with additional help being taken from Stack Overflow
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("This will delete everything. Are you sure?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton("Delete Everything", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteDB(); //this needs to be a function call
                    }})
                .setNegativeButton("No", null).show();

    }
    public void deleteDB(){
        db.delete(DB_TableName,null, null);
        Toast.makeText(this, "Database cleared", Toast.LENGTH_SHORT).show();
    }
}
