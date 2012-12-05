package com.example.cabertef.yus.act01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContactDescription extends Activity{
	
	private ContactsDataSource datasource;
	private int contactID;
	private EditText name, phone;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_description);
		
		name = (EditText)findViewById(R.id.namefield);
		phone = (EditText)findViewById(R.id.phonefield);
		Button done = (Button)findViewById(R.id.done);
		Button delete = (Button)findViewById(R.id.delete); 
		Bundle bundle = getIntent().getExtras();
		contactID = bundle.getInt("Contact ID");
		
		datasource = new ContactsDataSource(this);
		datasource.open();
		
		Contacts contactInfo = datasource.getContactAt(contactID);
		name.setText(contactInfo.getName());
		phone.setText(String.valueOf(contactInfo.getNumber()));

		done.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				datasource.editContact(contactID, String.valueOf(name.getText()), String.valueOf(phone.getText()));
				Intent intent = getIntent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		
		delete.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				datasource.deleteContact(contactID);
				Intent intent = getIntent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
	
}
