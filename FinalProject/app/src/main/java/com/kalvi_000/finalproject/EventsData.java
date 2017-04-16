package com.kalvi_000.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

/**
 * Created by kalvi_000 on 4/15/2017.
 */

public class EventsData extends SQLiteOpenHelper implements DatabaseConstants {

    public  EventsData(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION); //defines name and version

    }

    //Upon creation of the database, the following table will be built
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + DB_TableName + "  (" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, stuname TEXT NOT NULL, score INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS matches");
        onCreate(db);

    }


}
