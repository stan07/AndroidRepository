package com.example.cabertef.yus.act01;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContactsDataSource {
	 private SQLiteDatabase database;
	 private OurSQLiteHelper dbHelper;
	 private String[] columnArray = {OurSQLiteHelper.COLUMN_ID, OurSQLiteHelper.CONTACT_ID, OurSQLiteHelper.COLUMN_NAME, OurSQLiteHelper.COLUMN_PHONE};

	 public ContactsDataSource(Context context) {
		 dbHelper = new OurSQLiteHelper(context);
	 }

	 public void open() throws SQLException {
		 database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
		 dbHelper.close();
	 }
	 
	 public void createContact(Contacts contact) {
		    Cursor c = database.query(OurSQLiteHelper.TABLE_CONTACTS, columnArray, OurSQLiteHelper.CONTACT_ID+"="+contact.getConID(), null, null, null, null);
		    if(c.getCount() == 0)
		    {
		    	ContentValues values = new ContentValues();
		    	values.put(OurSQLiteHelper.CONTACT_ID, contact.getConID());
		    	values.put(OurSQLiteHelper.COLUMN_NAME, contact.getName());
		    	values.put(OurSQLiteHelper.COLUMN_PHONE, contact.getNumber());
		    	long insertId = database.insert(OurSQLiteHelper.TABLE_CONTACTS, null, values);
		    	Log.w("Inserted ID", ""+insertId);
		    }
	 }
	 
	 public void editContact(int id, String name, String num) {
		 ContentValues values = new ContentValues();
		 values.put(OurSQLiteHelper.COLUMN_NAME, name);
		 values.put(OurSQLiteHelper.COLUMN_PHONE, num);
		 database.update(OurSQLiteHelper.TABLE_CONTACTS, values, OurSQLiteHelper.COLUMN_ID+"="+id, null);
	 }
	 
	 public void deleteContact(int id) {
		 System.out.println("contacts deleted with id: " + id);
		 database.delete(OurSQLiteHelper.TABLE_CONTACTS, OurSQLiteHelper.COLUMN_ID + "=" + id, null);
	 }

	 public Contacts getContactAt(int id){
		 Cursor cursor = database.query(OurSQLiteHelper.TABLE_CONTACTS, columnArray, OurSQLiteHelper.COLUMN_ID + "=" + id, null, null, null, null);
		 if(cursor!=null && cursor.getCount()>0)
			 cursor.moveToFirst();
		 Contacts cntct = cursorToContacts(cursor);
		 
		 if(cursor!=null)
			 cursor.close();
		 
		 return cntct;		 
	 }
	 
	 public List<Contacts> getAllContacts() {
		 List<Contacts> contacts = new ArrayList<Contacts>();

		 Cursor cursor = database.query(OurSQLiteHelper.TABLE_CONTACTS, columnArray, null, null, null, null, null);

		 cursor.moveToFirst();
		 while (!cursor.isAfterLast()) {
			 Contacts contact = cursorToContacts(cursor);
			 contacts.add(contact);
			 cursor.moveToNext();
		 }
		    // Make sure to close the cursor
		    cursor.close();
		    return contacts;
	}
	 
	 private Contacts cursorToContacts(Cursor cursor) {
		 Contacts contact = new Contacts();
		 contact.setID(cursor.getInt(0));
		 contact.setConID(cursor.getInt(1));
		 contact.setName(cursor.getString(2));
		 contact.setNumber(cursor.getString(3));
		 return contact;
	 }
}
