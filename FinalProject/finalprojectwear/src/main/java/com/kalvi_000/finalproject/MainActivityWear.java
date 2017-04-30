package com.kalvi_000.finalproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivityWear extends WearableActivity{

    /*
    Message sending between wear client and mobile client was found in a tutorial from
    http://toastdroid.com/2014/08/18/messageapi-simple-conversations-with-android-wear/
    as well as code snippets taken from the examples github
    https://github.com/twotoasters/Wear-MessageApiDemo
    */

    //included on creation
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);


    //rebuild app
    private static final long CONNECTION_TIME_OUT_MS = 200;
    private static final String MESSAGE = "Hello Wear!";

    //included on creation
    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;

    private GoogleApiClient client;
    private String nodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);
        mClockView = (TextView) findViewById(R.id.clock);

        initGoogleAPI();
    }

    private void initGoogleAPI() {
        client = getGoogleApiClient(this);
        retrieveDeviceNode();
    }

    private GoogleApiClient getGoogleApiClient(Context context) {
        return new GoogleApiClient.Builder(context)
                .addApi(Wearable.API)
                .build();
    }

    private void retrieveDeviceNode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                NodeApi.GetConnectedNodesResult result =
                        Wearable.NodeApi.getConnectedNodes(client).await();
                List<Node> nodes = result.getNodes();
                if (nodes.size() > 0) {
                    nodeId = nodes.get(0).getId();
                }
                client.disconnect();
            }
        }).start();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }

    private void sendToast(final String Message) {
        if (nodeId != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    client.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                    Wearable.MessageApi.sendMessage(client, nodeId, Message, null);
                    client.disconnect();
                }
            }).start();
        }
    }

    public void makeToast(View view){
        Toast.makeText(this, "LaunchEnterMatches" ,Toast.LENGTH_SHORT).show();
        sendToast("LaunchEnterMatches");

        Intent sendIntent = new Intent(Intent.ACTION_MAIN);
        sendIntent.setComponent(new ComponentName("com.kalvi_000.finalproject","com.kalvi_000.finalproject.MainActivity"));
        startActivity(sendIntent);

        /*
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "LaunchEnterMatches");
        sendIntent.setType("text/plain");
        */
        startActivity(sendIntent);
    }

    public void viewMatches (View view){
        Toast.makeText(this, "LaunchViewMatches" ,Toast.LENGTH_SHORT).show();
        sendToast("LaunchViewMatches");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "LaunchViewMatches");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public void webViewACtivity (View view) {
        Toast.makeText(this, "LaunchWebView" ,Toast.LENGTH_SHORT).show();
        sendToast("LaunchWebView");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "LaunchWebView");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public void cardWebSearch(View view){
        Toast.makeText(this, "LaunchWebSearch" ,Toast.LENGTH_SHORT).show();
        sendToast("LaunchWebSearch");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "LaunchWebSearch");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void iconicCards (View view){
        Toast.makeText(this, "LaunchIconicCards" ,Toast.LENGTH_SHORT).show();
        sendToast("LaunchIconicCards");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "LaunchIconicCards");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
