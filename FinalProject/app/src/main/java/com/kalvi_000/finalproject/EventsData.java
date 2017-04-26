package com.kalvi_000.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

/**
 * Created by kalvi_000 on 4/15/2017.
 * This does Database stuff, not messing with it as much as we can
 */

class EventsData extends SQLiteOpenHelper implements DatabaseConstants {

    //database creation stuff
    EventsData(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION); //defines name and version

    }

    //Upon creation of the database, the following table will be built
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + DB_TableName + "  (" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, playlevel Integer, name TEXT NOT NULL, deckplayed TEXT NOT NULL, opponent TEXT NOT NULL, opponentdeck TEXT NOT NULL, result TEXT NOT NULL);");
    }

    //if we upgrade our database do this stuff, probably won't be used however
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TableName  );
        onCreate(db);

    }


}
