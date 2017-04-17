package com.example.scififanclub;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends ListActivity  {
	SQLiteDatabase db;
	Cursor cursor;
	EventsData events;
	int cntr = 1;
	static final int UPPER_LIMIT = 4;
	String[] items;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		events = new EventsData(this);
		db = events.getReadableDatabase();
		cursor = db.query("TopPicks",null, null, null, null, null, null);
		cursor.moveToFirst();
		String[] items = new String[] {cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4)};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		setListAdapter(adapter);
		
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();		
		if (cntr == UPPER_LIMIT) {
			cursor.moveToFirst();
			cntr = 1;				
		}
		else  {
			cursor.moveToNext();
			cntr++;
		}
		items = new String[] {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		setListAdapter(adapter);
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		switch (position) {
		case 1:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(cursor.getString(2))));
			break;
		case 2:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(cursor.getString(3))));
			break;
		case 3:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(cursor.getString(4))));
			break;
		};

	}

}
