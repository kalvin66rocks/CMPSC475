package com.example.gd.scifi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by GD on 3/19/2017.
 */

public class STrekNGFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.website_fragment, container, false);
        return view;

    }
    public void setWebPage(String url){
        WebView webview = (WebView) view.findViewById(R.id.webview);
        webview.loadUrl(url);
    }
}
