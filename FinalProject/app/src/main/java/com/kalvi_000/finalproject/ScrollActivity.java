package com.kalvi_000.finalproject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by Kevin on 4/23/2017.
 */

public class ScrollActivity extends Activity {

    //image views
    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;

    //color spinner
    Spinner color;

    //drawable object for images
    Drawable draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_layout);

        //connect all views and set up initial images
        connectViews();

        //set the color of the inially selected item
        changeCards(color.getSelectedItemPosition());
    }

    private void connectViews(){
        card1 = (ImageView) findViewById(R.id.imageView);

        card2 = (ImageView) findViewById(R.id.imageView2);

        card3 = (ImageView) findViewById(R.id.imageView3);

        card4 = (ImageView) findViewById(R.id.imageView4);

        card5 = (ImageView) findViewById(R.id.imageView5);


        //set up the spinner and use an adapter to fill it
        color = (Spinner) findViewById(R.id.spinnerColor);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this, R.array.Colors, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(colorAdapter);
        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //change the color of cards that we are displaying
                changeCards(color.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }

    public void changeCards(int selection){
        switch (selection){
            case 0: //white

                card1.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.armageddon);
                card1.setImageDrawable(draw);

                card2.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.balance);
                card2.setImageDrawable(draw);

                card3.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.swordstoplowshares);
                card3.setImageDrawable(draw);

                card4.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.thaliaguardian);
                card4.setImageDrawable(draw);

                card5.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.wrathofgod);
                card5.setImageDrawable(draw);
                break;
            case 1: //blue

                card1.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.brainstorm);
                card1.setImageDrawable(draw);

                card2.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ancestral_recall);
                card2.setImageDrawable(draw);

                card3.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.forceofwill);
                card3.setImageDrawable(draw);

                card4.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.counterbalance);
                card4.setImageDrawable(draw);

                card5.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.counterspell);
                card5.setImageDrawable(draw);
                break;
            case 2: //black

                card1.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.yawgwill);
                card1.setImageDrawable(draw);

                card2.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.thoughtseize);
                card2.setImageDrawable(draw);

                card3.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.hymntotourach);
                card3.setImageDrawable(draw);

                card4.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.demonictutor);
                card4.setImageDrawable(draw);

                card5.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.necropotence);
                card5.setImageDrawable(draw);
                break;
            case 3: //red

                card1.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.shivandragon);
                card1.setImageDrawable(draw);

                card2.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.balllightning);
                card2.setImageDrawable(draw);

                card3.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.priceofprogress);
                card3.setImageDrawable(draw);

                card4.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.lightningbolt);
                card4.setImageDrawable(draw);

                card5.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.wheeloffortune);
                card5.setImageDrawable(draw);
                break;
            case 4: //green

                card1.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.birdsofparadise);
                card1.setImageDrawable(draw);

                card2.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.khalnihydra);
                card2.setImageDrawable(draw);

                card3.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.scavengingooze);
                card3.setImageDrawable(draw);

                card4.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.survivalofthefittest);
                card4.setImageDrawable(draw);

                card5.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.tarmogoyf);
                card5.setImageDrawable(draw);
                break;
        }

    }
}
