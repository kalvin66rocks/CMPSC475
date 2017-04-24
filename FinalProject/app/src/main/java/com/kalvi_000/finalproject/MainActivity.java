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
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

import static android.content.ContentValues.TAG;

public class MainActivity extends ListActivity implements  DatabaseConstants{

    //database stuff
    private SQLiteDatabase db;
    private Cursor cursor;

    //variables for debugging the db
    private int dbID;
    private String dbName;
    private String dbDeckPlayed;
    private String dbOpponent;
    private String dbOpponentDeck;
    private String dbResult;
    private String dbPlayLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fill up the list view  with all of the main menu options
        String[] menuChoices = {"Enter a Match","View Match History", "Clear All Match History", "Look up a Card", "Favorite Cards", "Wizards Only!"};
        setListAdapter(new ArrayAdapter<>(this, R.layout.activity_main,R.id.menuOption, menuChoices));

        //function call to set dynamic shortcuts
        createShortCuts();

        //set up some database stuff
        //only needed in this activity to delete the entire database
        EventsData events;
        events = new EventsData(this);
        db = events.getWritableDatabase(); //open the database
    }

    //main listener to handle the main menu
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                //enter a match
                startActivity(new Intent(MainActivity.this, EnterMatches.class));
                break;
            case 1:
                /*
                //will be temporarily used to debug db via logcat
                cursor = db.query(DB_TableName, null, null, null, null, null, null);
                while (cursor.moveToNext()) {  //move to next row, if possible
                    dbID = cursor.getInt(0);
                    //I feel confident these comments can be removed soon
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
                */
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
                //favorite card scroll view activity (not built yet)
                startActivity(new Intent(MainActivity.this, ScrollActivity.class));
                break;
            case 5:
                //go to an activity with a webview that generates random facts and a built in webview that pulls up a random page from gatherer
                startActivity(new Intent(MainActivity.this, RandomCardWebView.class));
                break;
        }
    }

    private void createShortCuts(){
        //Dynamic Shortcut instructions found at https://catinean.com/2016/10/20/exploring-android-nougat-7-1-app-shortcuts/
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        //create dynamic shortcut to a website
        ShortcutInfo webShortcut = new ShortcutInfo.Builder(this, "shortcut_web")
                .setShortLabel("Card Search")
                .setLongLabel("Open wizard's website to search for cards")
                .setIcon(Icon.createWithResource(this, R.drawable.planeswalker_symbol))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://gatherer.wizards.com/Pages/Default.aspx")))
                .build();

        //create a dynamic shortcut to Enter Match
        ShortcutInfo dynamicShortcutEnter = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Enter a Match Result")
                .setLongLabel("Enter the Results of a Match Quickly")
                .setIcon(Icon.createWithResource(this, R.drawable.planeswalker_symbol))
                .setIntents(
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                                new Intent(EnterMatches.ACTION)
                        })
                .build();

        //create a dynamic shortcut to view matches
        ShortcutInfo dynamicShortcutView = new ShortcutInfo.Builder(this, "id2")
                .setShortLabel("View Match Results")
                .setLongLabel("View Match Results")
                .setIcon(Icon.createWithResource(this, R.drawable.planeswalker_symbol))
                .setIntents(
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                                new Intent(ViewMatches.VIEW)
                        })
                .build();

        //add all the dynamic shortcuts
        shortcutManager.setDynamicShortcuts(Arrays.asList(webShortcut, dynamicShortcutEnter, dynamicShortcutView));
    }

    //confirmation dialog instructions found within the AndroidSDK documenation and figuring stuff out in android Studio

    //first delete confirmation
    private void deleteDialog(){
        final MediaPlayer warning = MediaPlayer.create(this, R.raw.airhorn);
        warning.start();
        //alert dialog information found on Android Documentation with additional help being taken from Stack Overflow
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("This will delete all matches. Are you sure?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        confirmDeleteDialog(); //this needs to be a function call not sure why but it makes things function correctly
                    }})
                .setNegativeButton("No", null).show();

    }

    //final deletion confirmation
    private void confirmDeleteDialog(){
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

    //function to delete all the entries in the database
    private void deleteDB(){
        db.delete(DB_TableName,null, null);
        Toast.makeText(this, "Database cleared", Toast.LENGTH_SHORT).show();
    }


}
