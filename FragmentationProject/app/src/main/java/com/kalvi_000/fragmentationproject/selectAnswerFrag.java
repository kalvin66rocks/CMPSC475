package com.kalvi_000.fragmentationproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by kalvi_000 on 3/30/2017.
 */

public class selectAnswerFrag extends Fragment {

    private OnItemSelectedListener listener;
    private CheckBox answer1;
    private CheckBox answer2;
    private CheckBox answer3;
    private CheckBox answer4;
    private CheckBox answer5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_answer_frag, container, false);
        answer1 = (CheckBox) view.findViewById(R.id.answer1);
        answer1.setOnClickListener(answerSelectedlistener);
        answer2 = (CheckBox) view.findViewById(R.id.answer2);
        answer2.setOnClickListener(answerSelectedlistener);
        answer3 = (CheckBox) view.findViewById(R.id.answer3);
        answer3.setOnClickListener(answerSelectedlistener);
        answer4 = (CheckBox) view.findViewById(R.id.answer4);
        answer4.setOnClickListener(answerSelectedlistener);
        answer5 = (CheckBox) view.findViewById(R.id.answer5);
        answer5.setOnClickListener(answerSelectedlistener);

        return view;
    }

    private View.OnClickListener answerSelectedlistener = new View.OnClickListener() {
        public void onClick(View view) {
            int theSelection = -1;
            switch (view.getId()) {
                case R.id.answer1: //answer 1
                    //clear all other checkboxes
                    theSelection = 0;
                    break;
                case R.id.answer2: //answer2
                    //clear all other checkboxes
                    theSelection = 1;
                    break;
                case R.id.answer3: //answer3
                    //clear all other checkboxes
                    theSelection = 2;
                    break;
                case R.id.answer4: //answer3
                    //clear all other checkboxes
                    theSelection = 3;
                    break;
                case R.id.answer5: //answer3
                    //clear all other checkboxes
                    theSelection = 4;
                    break;
            }
            listener.onAnswerItemSelected(theSelection);
        }
    };

    public interface OnItemSelectedListener{
        public void onAnswerItemSelected(int positiveNumber);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnItemSelectedListener) activity;
    }
}