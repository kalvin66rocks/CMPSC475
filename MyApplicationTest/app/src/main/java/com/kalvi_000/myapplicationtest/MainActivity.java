package com.kalvi_000.myapplicationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView greetingTextView;
    private TextView weAreTextView;
    private ImageView creepyImageView;

    //is hello displayed
    private boolean isHello;
    private boolean isCreepy;
    private boolean weAre;
    Button creepyButton;
    Button weAreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android does these things
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //establish a reference to the textview and imageview
        greetingTextView = (TextView) findViewById(R.id.textView);
        creepyImageView = (ImageView) findViewById(R.id.imageView);
        weAreTextView = (TextView) findViewById(R.id.textView2);

        //initialize both text and image
        initialize();

        //register the listener for button 1 & 2
        Button exclaimBtn = (Button) findViewById(R.id.button);
        exclaimBtn.setOnClickListener(toggleGreeting);

        creepyButton = (Button) findViewById(R.id.button2);
        creepyButton.setOnClickListener(togglePicture);

        weAreButton = (Button) findViewById(R.id.button3);
        weAreButton.setOnClickListener(togglePennState);
    }
    private final View.OnClickListener toggleGreeting = new View.OnClickListener(){
        public void onClick(View btn)
        {
            if(isHello){
                isHello = false;
                greetingTextView.setText(R.string.goodbye);
            }else{
                isHello=true;
                greetingTextView.setText(R.string.hello);
            }
        }
    };

    private final View.OnClickListener togglePicture = new View.OnClickListener(){
        public void onClick(View btn){
            if(isCreepy){
                isCreepy = false;
                creepyImageView.setVisibility(View.INVISIBLE);
                creepyButton.setText(R.string.creepy);
            } else {
                isCreepy = true;
                creepyImageView.setVisibility(View.VISIBLE);
                creepyButton.setText(R.string.not_creepy);
            }
        }
    };

    private final View.OnClickListener togglePennState = new View.OnClickListener() {
        public void onClick(View btn) {
            if (weAre) {
                weAre = false;
                weAreTextView.setText(R.string.pennState);
            } else {
                weAre = true;
                weAreTextView.setText(R.string.weAre);
            }
        }
    };

    private void initialize(){
        isHello=true;
        isCreepy=true;
        weAre=true;
    }
}
