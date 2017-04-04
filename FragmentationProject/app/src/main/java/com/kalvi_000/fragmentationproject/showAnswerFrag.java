package com.kalvi_000.fragmentationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kevin on 4/3/2017.
 */

public class showAnswerFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_answer_frag, container, false);
        return view;
    }

    public void setAnswerText(int positiveNumber, TextView answerBox){
        switch(positiveNumber){
            case 0:
                answerBox.setText(R.string.standard_answer);
                break;
            case 1:
                answerBox.setText(R.string.modern_answer);
                break;
            case 2:
                answerBox.setText(R.string.legacy_answer);
                break;
            case 3:
                answerBox.setText(R.string.vintage_answer);
                break;
            case 4:
                answerBox.setText(R.string.extended_answer);
                break;
        }
    }
}
