package com.example.gd.startrekactors;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by GD on 3/26/2017.
 */

public class MyActorSelFrag extends Fragment {
    private  OnItemSelectedListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actor_selections_frag, container, false);

        Button spockButton = (Button) view.findViewById(R.id.button);
        spockButton.setOnClickListener(ActorsChangeListener);
        Button kirkButton = (Button) view.findViewById(R.id.button2);
        kirkButton.setOnClickListener(ActorsChangeListener);
        Button scottyButton = (Button) view.findViewById(R.id.button3);
        scottyButton.setOnClickListener(ActorsChangeListener);
        return view;
    }

    private OnClickListener ActorsChangeListener = new OnClickListener() {
        public void onClick(View view) {
            int theSelection = -1;
            switch (view.getId()) {
                case R.id.button: //spock
                    theSelection = 0;
                    break;
                case R.id.button2: //kirk
                    theSelection = 1;
                    break;
                case R.id.button3: //scotty
                    theSelection = 2;
                    break;
            }
            Log.d("Dudas", "A");
            listener.onActorItemSelected(theSelection);
        }
    };

    public interface OnItemSelectedListener {
        public void onActorItemSelected(int posit);
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        listener = (OnItemSelectedListener) activity;
    }
}
