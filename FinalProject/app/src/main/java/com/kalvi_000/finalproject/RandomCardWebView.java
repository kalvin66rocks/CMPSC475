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
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Kevin on 4/19/2017.
 */

public class RandomCardWebView extends Activity {

    WebView randomCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_random_card);
        String url="file:///android_asset/webview.html";
        WebView cardfetcher = (WebView) findViewById(R.id.webView1);
        cardfetcher.setWebChromeClient(new WebChromeClient()); //use chrome
        cardfetcher.getSettings().setJavaScriptEnabled(true);//enable js
        cardfetcher.addJavascriptInterface(new JavaScriptInterface(this),"Android");
        cardfetcher.loadUrl(url);

        randomCard = (WebView) findViewById(R.id.webView2);
        //randomCard.setWebChromeClient(new WebChromeClient()); //use chrome
        String RandomUrl="http://gatherer.wizards.com/Pages/Card/Details.aspx?action=random";
        randomCard.loadUrl(RandomUrl);
    }

    public class JavaScriptInterface{
        Context myContext;

        JavaScriptInterface(Context c){
            myContext = c;
        }

        @JavascriptInterface
        public void ShowToast(String toastMsg){
            Toast.makeText(myContext,toastMsg,Toast.LENGTH_LONG).show();
        }
        @JavascriptInterface
        public void RandomCard(){
            String RandomUrl="http://gatherer.wizards.com/Pages/Card/Details.aspx?action=random";
            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RandomUrl)));
            Toast.makeText(myContext,RandomUrl,Toast.LENGTH_LONG).show();
            randomCard.loadUrl(RandomUrl);

        }
    }
}
