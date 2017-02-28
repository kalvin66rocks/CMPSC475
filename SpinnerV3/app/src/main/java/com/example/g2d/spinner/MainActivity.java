package com.example.g2d.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner mSpinner, mSpinner2;
    Toast msg;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner2 = (Spinner) findViewById(R.id.spinner2);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mButton = (Button)findViewById(R.id.button);
        // How does the single item appear
        ArrayAdapter<CharSequence> nameAdapter = ArrayAdapter.createFromResource(this, R.array.scifimovies, android.R.layout.simple_spinner_item);
        // How does the dropdown list appear
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //simple_spinner_item is more compact
        mSpinner.setAdapter(nameAdapter);

    }

    public void ProcessSpinners(View view) {
        msg = Toast.makeText(this, "Hello", Toast.LENGTH_LONG);
        msg.show();
        msg = Toast.makeText(this,
                (mSpinner.getSelectedItem().toString() + "-"+ mSpinner2.getSelectedItem().toString()),Toast.LENGTH_LONG);
        msg.show();
        msg = Toast.makeText(this,
                (mSpinner.getSelectedItemPosition() + "-"+ mSpinner2.getSelectedItemPosition()),Toast.LENGTH_LONG);
        msg.show();

    }

}
