package com.example.cabertef.yus.act01;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DatabaseActivity extends Activity {
	
	private int id;
	private String name, num;
	private List<Contacts> contactList;
	private ContactsDataSource datasource;
	private Intent intent;
	private ListView list;
	
	
    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);   
        
        	datasource = new ContactsDataSource(DatabaseActivity.this);
        	datasource.open();
        	contactList = new ArrayList<Contacts>();
        	
	        ContentResolver resolver = getContentResolver();
	        Cursor c = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	        
	        //contactList = new Contacts[c.getCount()];
	        
	        if(c.getCount()>0) {
	        	while(c.moveToNext()) {
	        		id = c.getInt(c.getColumnIndex(ContactsContract.Contacts._ID));
	        		name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	            
	        		if(Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
        			
	        			Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, 
        						           ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+id,
        						           null, null);
        			
	        			while(cursor.moveToNext()){
	        				num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	        				System.out.println("name: "+name+",ID: "+id+",phone: "+num);
	        				//contactList[c.getPosition()] = new Contacts(id, name, num);
	        				datasource.createContact(new Contacts(id, name, num));
	        			}
	        			cursor.close();
	            	}
	        	}
	        } 
	        
	        displayContacts();
	        
	        list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view, int pos,
						long id) {
					intent = new Intent();
					//intent = new Intent(DatabaseActivity.this, ContactDescription.class);
					intent.putExtra("Contact ID", contactList.get(pos).getID());
					intent.setClass(DatabaseActivity.this, ContactDescription.class);
					startActivityForResult(intent, 1);
				}	        	
	        });
    	} 
    
    
    private void displayContacts() {
    	 	contactList = datasource.getAllContacts();    
	        ContactAdapter adapter = new ContactAdapter(this, (ArrayList<Contacts>)contactList);
	        list = (ListView) findViewById(R.id.listview);
	        list.setAdapter(adapter);
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1 && resultCode == RESULT_OK){
			displayContacts();
		}
	}
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_database, menu);
        return true;
    }
}
