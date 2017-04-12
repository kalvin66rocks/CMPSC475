package com.example.firstdatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener, Constants {
	
	private EventsData events;
	Button btnAdd, btnClose;
    EditText editTextDelete, editTextQOne;
	Button btnDelete, btnQueryOne, btnQuery, btnUpdate;
	SQLiteDatabase db;
	Cursor cursor;  // Cursor holds a returned query
	String aName, strScore, deleteID;
	int aScore, aID, i=1;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnDelete = (Button)findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);
        editTextDelete = (EditText)findViewById(R.id.editTextDelete);

		btnQueryOne = (Button)findViewById(R.id.btnQueryOne);
		btnQueryOne.setOnClickListener(this);
        editTextQOne = (EditText)findViewById(R.id.editTextQOne);

		btnQuery = (Button)findViewById(R.id.btnQuery);
		btnQuery.setOnClickListener(this);	
		btnUpdate = (Button)findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(this);	
		btnClose = (Button)findViewById(R.id.btnClose);
		btnClose.setOnClickListener(this);
		
		events = new EventsData(this);
		db = events.getWritableDatabase();	//opens the data base for read/write operations	
	}

	@Override
	public void onClick(View v) {
		ContentValues someValues = new ContentValues();	//represents a single row
		
		switch (v.getId()) {
		case R.id.btnAdd:  //add 2 rows into the database
			someValues.put("stuname", "Jack Smith2");
			someValues.put("score",67);
			db.insert("students", null, someValues);
			someValues.put("stuname", "Mary Trarry2");
			someValues.put("score",100);
	        //table - the name of the table to run the query against
	        //nullColumnHack - prevents an empty row from being inserted by specifying a column name to insert a null into (usually set to null)
	        //values - a ContentValues map that specifies the column name/value pairs to be inserted into the row.	
			db.insert(DB_TableName, null, someValues);
			break;
		case R.id.btnDelete:
			db.delete(DB_TableName, "_id=" + editTextDelete.getText(),null); //pull in auto number of record to delete
			break;
		case R.id.btnQueryOne:
            cursor = db.query(DB_TableName, null, "_id=" + editTextQOne.getText(), null, null, null, null);
			cursor.moveToFirst(); // initially set at 0 or nothing
            aID = cursor.getInt(0);  //ready for some future processing
            Log.d("Query*****", Integer.toString(aID));
			aName = cursor.getString(1);  //ready for some future processing
            Log.d("Query*****", aName);
			strScore = cursor.getString(2);
            Log.d("Query*****", strScore);
			aScore = Integer.parseInt(strScore); //ready for some future processing
			break;
		case R.id.btnQuery:
			cursor = db.query(DB_TableName, null, null, null, null, null, null);
			//row is set to 0 after initial query
			while (cursor.moveToNext()){  //move to next row, if possible
				aID = cursor.getInt(0);  //some future processing could be done
                Log.d("Query*****", Integer.toString(aID));
				aName = cursor.getString(1); //some future processing could be done
                Log.d("Query*****", aName);
				strScore = cursor.getString(2);
                Log.d("Query*****", strScore);
				aScore = Integer.parseInt(strScore);  //some future processing could be done
			}
			break;
		case R.id.btnUpdate:
			someValues.put("stuname", "Jack Snorkle");
			someValues.put("score",58);  
			String[] whereArgs= null;
	        //table - the name of the table to run the query against
	        //values - a ContentValues map that specifies column names/new values to be updated
	        //whereClause - filter to specify matching criteria to only update particular matching rows (null updates all rows)
	        //whereArgs - a list of values to substitute for any placeholders in the whereClause
			db.update(DB_TableName, someValues, "_id=2", whereArgs);
		    break;
		case R.id.btnClose:
			db.close(); //close database
            finish();  //finish activity
			break;			
		}
		
	}



}
