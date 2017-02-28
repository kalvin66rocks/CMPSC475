package com.kalvi_000.brennemanbanjorentals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import static com.kalvi_000.brennemanbanjorentals.R.styleable.View;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //create a rentBanjos item
    private rentBanjos rent;

    //create objects to modify the UI
    private EditText banjosET;
    private EditText casesET;
    private TextView rentCostTV;
    private TextView taxCostTV;
    private TextView totalCostTV;
    private TextView insuranceCostTV;
    private Switch mySwitch;
    private Spinner instrumentSpinner;
    private Toast msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android default stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call the constructor for rentBanjos
        rent = new rentBanjos();


        //connect the two Edit Text Views
        banjosET = (EditText) findViewById(R.id.numBanjosET);
        casesET = (EditText) findViewById(R.id.numCasesET);

        //do spinner stuff here
        instrumentSpinner = (Spinner) findViewById(R.id.spinner);
        //single item in dropdown list
        ArrayAdapter<CharSequence> instrumentAdapter = ArrayAdapter.createFromResource(this, R.array.instrumentNames, android.R.layout.simple_spinner_item);
        //set the dropdown list appearance
        instrumentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instrumentSpinner.setAdapter(instrumentAdapter);

        //connect to the textviews
        rentCostTV = (TextView) findViewById(R.id.baseRentalValue);
        taxCostTV = (TextView) findViewById(R.id.salesTaxValue);
        totalCostTV = (TextView) findViewById(R.id.totalCostValue);
        insuranceCostTV = (TextView) findViewById(R.id.insuranceValue);

        //create some listeners for whenever the user has input
        banjosET.addTextChangedListener(banjoTextWatcher);
        casesET.addTextChangedListener(caseTextWatcher);

        //connect the switch
        mySwitch = (Switch) findViewById(R.id.switch2);
        mySwitch.setChecked(false);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //insurance yes
                if (isChecked) {
                    DecimalFormat df = new DecimalFormat("#.00");
                    rent.updateInsurance(true);
                    insuranceCostTV.setText("$" + df.format(rent.getinsurance()));
                    taxCostTV.setText("$" + df.format(rent.getTax()));
                    totalCostTV.setText("$" + df.format(rent.getTotal()));
                }
                //insurance no
                else {
                    DecimalFormat df = new DecimalFormat("#.00");
                    rent.updateInsurance(false);
                    insuranceCostTV.setText("$" + df.format(rent.getinsurance()));
                    taxCostTV.setText("$" + df.format(rent.getTax()));
                    totalCostTV.setText("$" + df.format(rent.getTotal()));
                }
            }
        });


        instrumentSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rent.updateInstrumentSelected(instrumentSpinner.getSelectedItemPosition());
        updateFields();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //don't think this needs to do anything
    }

    private TextWatcher banjoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                rent.updateBanjos(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                rent.updateBanjos(0);
            }
            updateFields();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher caseTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                rent.updateCases(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                rent.updateCases(0);
            }
            updateFields();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /*public void ProcessSpinners(View view){
        rent.updateInstrumentSelected(instrumentSpinner.getSelectedItemPosition());

        msg = Toast.makeText(this,
                (instrumentSpinner.getSelectedItemPosition()),Toast.LENGTH_SHORT);
        msg.show();

    }*/

    public void updateFields(){
        DecimalFormat df = new DecimalFormat("#.00");
        rentCostTV.setText("$" + df.format(rent.getRental()));
        taxCostTV.setText("$" + df.format(rent.getTax()));
        totalCostTV.setText("$" + df.format(rent.getTotal()));
        insuranceCostTV.setText("$" + df.format(rent.getinsurance()));
    }
}