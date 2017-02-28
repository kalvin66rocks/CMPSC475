package com.kalvi_000.tapbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //model
    private Counter count;
    private boolean allowNegative;
    private int countBy = 1;

    //view
    private TextView countView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkbox stuff for negative handling
        final CheckBox negative = (CheckBox) findViewById(R.id.checkBox);
        negative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            //Compound button, button with two states, checked and unchecked
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    allowNegative = true;
                }else{
                    allowNegative = false;
                }
            }
        });

        final RadioButton oneRB = (RadioButton) findViewById(R.id.radioButtonOne);
        final RadioButton fiveRB = (RadioButton) findViewById(R.id.radioButtonFive);
        final RadioButton tenRB = (RadioButton) findViewById(R.id.radioButtonTen);
        oneRB.setChecked(true);

        oneRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(oneRB.isChecked()) {
                    countBy = 1;
                }
            }
        });
        fiveRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fiveRB.isChecked()) {
                    countBy = 5;
                }
            }
        });
        tenRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(tenRB.isChecked()) {
                    countBy = 10;
                }
            }
        });

        count = new Counter();
        countView = (TextView) findViewById(R.id.textView);
    }

    public void increaseCount (View view){
        count.addCount(countBy);
        countView.setText(count.getCount().toString());
    }

    public void decreaseCount (View view){
        count.subCount(allowNegative,countBy);
        countView.setText(count.getCount().toString());
    }
}
