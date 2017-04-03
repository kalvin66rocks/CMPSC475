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
        answer1.setOnClickListener(answerSelectedListener);
        answer2 = (CheckBox) view.findViewById(R.id.answer2);
        answer2.setOnClickListener(answerSelectedListener);
        answer3 = (CheckBox) view.findViewById(R.id.answer3);
        answer3.setOnClickListener(answerSelectedListener);
        answer4 = (CheckBox) view.findViewById(R.id.answer4);
        answer4.setOnClickListener(answerSelectedListener);
        answer5 = (CheckBox) view.findViewById(R.id.answer5);
        answer5.setOnClickListener(answerSelectedListener);

        return view;
    }

    private View.OnClickListener answerSelectedListener = new View.OnClickListener() {
        public void onClick(View view) {
            int theSelection = -1;
            switch (view.getId()) {
                case R.id.answer1: //answer 1
                    //clear all other checkboxes
                    answer2.setChecked(false);
                    answer3.setChecked(false);
                    answer4.setChecked(false);
                    answer5.setChecked(false);
                    theSelection = 0;
                    break;
                case R.id.answer2: //answer2
                    //clear all other checkboxes
                    answer1.setChecked(false);
                    answer3.setChecked(false);
                    answer4.setChecked(false);
                    answer5.setChecked(false);
                    theSelection = 1;
                    break;
                case R.id.answer3: //answer3
                    //clear all other checkboxes
                    answer1.setChecked(false);
                    answer2.setChecked(false);
                    answer4.setChecked(false);
                    answer5.setChecked(false);
                    theSelection = 2;
                    break;
                case R.id.answer4: //answer3
                    //clear all other checkboxes
                    answer1.setChecked(false);
                    answer2.setChecked(false);
                    answer3.setChecked(false);
                    answer5.setChecked(false);
                    theSelection = 3;
                    break;
                case R.id.answer5: //answer3
                    //clear all other checkboxes
                    answer1.setChecked(false);
                    answer2.setChecked(false);
                    answer3.setChecked(false);
                    answer4.setChecked(false);
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
