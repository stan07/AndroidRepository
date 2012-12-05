package com.example.cabertef.yus.act01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OurSQLiteHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_CONTACTS = "Contacts";
	public static final String COLUMN_ID = "ID";
	public static final String CONTACT_ID = "CONTACTID";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_PHONE = "PHONE";
	private static final String DATABASE_NAME = "DBAct1.db";
	private static final int DATABASE_VERSION = 1;
	
	 private static final String DATABASE_CREATE = "create table "
		     + TABLE_CONTACTS + "(" 
			 + COLUMN_ID + " integer primary key autoincrement, " 
			 + CONTACT_ID + " integer not null, "
		     + COLUMN_NAME + " text not null, " 
			 + COLUMN_PHONE + " text not null);";
	
	public OurSQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int olddb, int newdb) {
		Log.w(OurSQLiteHelper.class.getName(), "Upgrading old database version: " + olddb + " to new database version: " + newdb);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		onCreate(db);		
	}
	
}
