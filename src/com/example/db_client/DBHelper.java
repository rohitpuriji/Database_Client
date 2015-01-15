package com.example.db_client;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//	    public static final String KEY_ID = "no";
//	    public static final String KEY_NAME = "name";
//
//	    private static final String TAG = "DBAdapter";
//	    private static final String DATABASE_NAME = "SQLiteDB";
//
//	    private static final String TABLE_NAME = "Department";
//	    private static final int DATABASE_VERSION = 1;
//
//	    private static final String CREATE_TABLE = 
//	    		"create table departmentList (id INTEGER PRIMARY KEY, name TEXT);";

//	    public static final String KEY_ID = "no";
//	    public static final String KEY_NAME = "name";
//
//	    private static final String TAG = "DBAdapter";
//	    private static final String DATABASE_NAME = "SQLiteDB";
//
//	    private static final String TABLE_NAME = "Department";
//	    private static final int DATABASE_VERSION = 1;
//
//	    private static final String CREATE_TABLE = 
//	    		"create table departmentList (id INTEGER PRIMARY KEY, name TEXT);";

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "SQLiteDB";
	public static final String CONTACTS_TABLE_NAME = "Department";
	public static final String CONTACTS_COLUMN_UNIQUE_ID = "no";
	public static final String CONTACTS_COLUMN_NAME = "name";

	//constructor for database class DBHelper....
	public DBHelper(Context context)
	{
		super(context, DATABASE_NAME , null, 1);
	}

	//override onCreate method to create Database table...
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// TODO Auto-generated method stub.
		Log.e("DB created", "yes");
		db.execSQL("create table Department " + "(id text, name text)");
	}

	//Upgrade new Table...
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS Department");
		onCreate(db);
	}

	//to insert new records in table...
	public boolean insertContact  (String name, String unique_id)
	{
		Log.e("DB insert call", "yes");

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("name", name);
		contentValues.put("id", unique_id);	

		db.insert("Department", null, contentValues);
		return true;
	}

	//get data from table...
	public Cursor getData(String id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.rawQuery( "select * from Department where id="+id+"", null );
		return res;
	}

	//get no of rows in table...
	public int numberOfRows(){
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
		return numRows;
	}


	//to delete data from table at particular id....
	public Integer deleteContact (String id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("Department","id = '"+id+"'", null);
	}
	//to get all data from database...
	public ArrayList getAllCotacts() 
	{
		ArrayList array_list = new ArrayList();
		//hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.rawQuery( "select * from Department", null );
		res.moveToFirst();
		while(res.isAfterLast() == false)
		{
			array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
			array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_UNIQUE_ID)));

			res.moveToNext();
		}
		return array_list;
	}
}


