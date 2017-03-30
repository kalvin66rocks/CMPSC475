package com.example.gd.startrekactors;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by GD on 3/27/2017.
 */

public class ShowActorFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actor_picture_frag, container, false);

        return view;
    }

    public void setPicture(int posit, ImageView iv) {
        Log.d("Dudas****", Integer.toString(posit));

        switch (posit) {
            case 0:
                iv.setImageResource(R.drawable.spock);
                break;
            case 1:
                iv.setImageResource(R.drawable.kirk);
                break;
            case 2:
                iv.setImageResource(R.drawable.scotty);
                break;
        }
    }

}

