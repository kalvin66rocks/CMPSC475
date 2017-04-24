package com.kalvi_000.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Kevin on 4/19/2017.
 * This will control the android side of the webview stuff
 */

public class RandomCardWebView extends Activity {

    WebView randomCard;
    String RandomUrl = "http://gatherer.wizards.com/Pages/Card/Details.aspx?action=random";
    String [] facts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_random_card);

        //load in the array
        facts = getResources().getStringArray(R.array.RandomFacts);

        String url="file:///android_asset/webview.html";
        WebView cardfetcher = (WebView) findViewById(R.id.webView1);
        cardfetcher.setWebChromeClient(new WebChromeClient()); //use chrome
        cardfetcher.getSettings().setJavaScriptEnabled(true);//enable js
        cardfetcher.addJavascriptInterface(new JavaScriptInterface(this),"Android");
        cardfetcher.loadUrl(url);

        randomCard = (WebView) findViewById(R.id.webView2);
        //chrome causes chrome browser to open webview does not
        randomCard.setWebViewClient(new WebViewClient());
        randomCard.setVisibility(View.INVISIBLE);
        Button myButton = (Button) findViewById(R.id.button2);
        //according to errors and documentation, setting the listener for the button here is the only way to properly do things whenever a webview is involved
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomCard.loadUrl(RandomUrl);
                randomCard.setVisibility(View.VISIBLE);
            }
        });

    }

    //make javascript for my webview
    public class JavaScriptInterface{
        Context myContext;

        JavaScriptInterface(Context c){
            myContext = c;
        }

        //I don't think I'm using this so it will end up removed
        @JavascriptInterface
        public void ShowToast(String toastMsg){
            Toast.makeText(myContext,toastMsg,Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void RandomFact(){
            Random rand = new Random();
            int selection = rand.nextInt(5);
            Toast.makeText(myContext,facts[selection],Toast.LENGTH_LONG).show();

        }
    }
}
