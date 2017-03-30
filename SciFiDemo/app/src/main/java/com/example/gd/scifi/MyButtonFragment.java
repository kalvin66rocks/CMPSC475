package com.example.gd.scifi;

import android.app.Activity;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by GD on 3/19/2017.
 */

public class MyButtonFragment extends Fragment {
    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_button_fragment,container,false);

        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(StarTrekShowChangeListener);
        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(StarTrekShowChangeListener);
        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(StarTrekShowChangeListener);
        return view;
    }
    private View.OnClickListener StarTrekShowChangeListener = new OnClickListener(){
        public void onClick(View view){

            switch (view.getId()){
                case R.id.button1:
                listener.onWebSiteItemSelected("http://www.startrek.com/database_article/star-trek-the-next-generation-synopsis");
                    break;
                case R.id.button2:
                    listener.onWebSiteItemSelected("http://www.startrek.com/database_article/star-trek-voyager-synopsis");
                    break;
                case R.id.button3:
                    listener.onWebSiteItemSelected("http://www.startrek.com/database_article/star-trek-deep-space-nine-synopsis");
                    break;
            }
        }
    };
    public interface OnItemSelectedListener {
        public void onWebSiteItemSelected(String link);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener=(OnItemSelectedListener) activity;
    }
}
