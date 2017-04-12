package com.example.firstdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

public class EventsData extends SQLiteOpenHelper implements Constants {
		
	public  EventsData(Context ctx) {
		super(ctx, DB_NAME, null, DB_VERSION); //defines name and version
	
	}

	//Upon creation of the database, the following table will be built
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(" CREATE TABLE " + DB_TableName + "  (" + _ID + 
				" INTEGER PRIMARY KEY AUTOINCREMENT, stuname TEXT NOT NULL, score INTEGER);");		
	}

	//Check the version numbers, if the version number on disk does not match this version number then
	//execute the following code
	//Note:  This is the simplest fix, just delete the old table and create a new one
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS students");
		onCreate(db);
	
	}


}
