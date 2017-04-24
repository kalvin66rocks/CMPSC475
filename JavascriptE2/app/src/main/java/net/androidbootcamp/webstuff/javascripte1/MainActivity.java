package net.androidbootcamp.webstuff.javascripte1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url="file:///android_asset/javasc2.html";
        WebView webview =(WebView) findViewById(R.id.webView);
        webview.setWebChromeClient(new WebChromeClient()); //enable Chrome
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JavaScriptInterface(this),"Android"); // set interface name
        webview.loadUrl(url);
        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://stargate.mgm.com")));
            }
        });
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
    public void IncrementNumber(String aNumber){
        int someNumber = Integer.parseInt(aNumber);
        someNumber++;
        String aString = Integer.toString(someNumber);
        Toast.makeText(myContext,aString,Toast.LENGTH_LONG).show();
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
