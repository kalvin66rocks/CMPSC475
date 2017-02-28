package com.kalvi_000.switchexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView outputofSwitchTXTV;
    private Switch mySwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputofSwitchTXTV = (TextView) findViewById(R.id.outputofSwitchTXTV);
        mySwitch = (Switch) findViewById(R.id.Switch1);
        mySwitch.setChecked(false);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    outputofSwitchTXTV.setText("Set On");
                else
                    outputofSwitchTXTV.setText("Set Off");
            }
        });
    }
}
