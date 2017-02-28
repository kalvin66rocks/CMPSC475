package com.example.gd.revchkboxradbtn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener, View.OnLongClickListener {
    CheckBox firstCB, secondCB, thirdCB;
    RadioButton firstRB, secondRB, thirdRB;
    Button button1, button2, button3;
    boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstCB = (CheckBox) findViewById(R.id.checkBox1);
        secondCB = (CheckBox) findViewById(R.id.checkBox2);
        thirdCB = (CheckBox) findViewById(R.id.checkBox3);
        thirdCB.setOnCheckedChangeListener(this);
        thirdCB.setOnClickListener(this);

        firstRB = (RadioButton) findViewById(R.id.radioButton1);
        secondRB = (RadioButton) findViewById(R.id.radioButton2);
        thirdRB = (RadioButton) findViewById(R.id.radioButton3);

        thirdRB.setOnCheckedChangeListener(this);

        firstCB.setChecked(true); // turned second one in xml
        firstRB.setChecked(true);
        boolean checked = true;
        secondRB.setChecked(checked);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button1.setOnLongClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.radioButton3:
                if (isChecked) {
                    Toast a = Toast.makeText(MainActivity.this,
                            "third radiobutton is checked", Toast.LENGTH_SHORT);
                    a.show();
                }
                if (buttonView.getId() == R.id.radioButton3)
                    Toast.makeText(MainActivity.this, "third radiobutton pressed",
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox3:
                if (isChecked) {
                    Toast a = Toast.makeText(MainActivity.this,
                            "third checkbox checked", Toast.LENGTH_SHORT);
                    a.show();
                }
                if (buttonView.getId() == R.id.checkBox3)
                    Toast.makeText(MainActivity.this,
                            "third checkbox button pressed", Toast.LENGTH_SHORT)
                            .show();
                break;
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.button3:
                firstRB.setChecked(false);
                secondRB.setChecked(false);
                thirdRB.setChecked(false);
                break;
            case R.id.button2:
                firstRB.toggle();
                if (firstRB.isChecked()) {
                    Toast a = Toast.makeText(MainActivity.this, "first radiobox",
                            Toast.LENGTH_SHORT);
                    a.show();
                }
                secondRB.toggle();
                break;
            case R.id.button1:
                firstCB.toggle();
                if (firstCB.isChecked()) {
                    Toast a = Toast.makeText(MainActivity.this, "first checkbox",
                            Toast.LENGTH_SHORT);
                    a.show();
                }
                secondCB.toggle();
                break;
            case R.id.checkBox3: // NEW ****************************************
                Toast.makeText(MainActivity.this, "third checkbox clicked",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onLongClick(View arg0) {
        // only set listener on button 1
        if (toggle) {
            Toast.makeText(MainActivity.this, "Long - no stop", Toast.LENGTH_SHORT)
                    .show();
            toggle = false;
            return false;
        } else {
            Toast.makeText(MainActivity.this, "Long - stop",
                    Toast.LENGTH_SHORT).show();
            toggle = true;
            return true;
        }
    }

}
