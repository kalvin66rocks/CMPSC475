package com.kalvi_000.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends ListActivity {

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
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, EnterMatches.class));
                break;
            case 1:
                //startActivity(new Intent(MainActivity.this, HowToPlay.class));
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
