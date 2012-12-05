package com.example.cabertef.yus.act01;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contacts>{
	
	Context context;
	Contacts[] contactArray;
	
	public ContactAdapter(Context context, ArrayList<Contacts> contactList) {
		super(context, R.layout.contact_list, contactList);
		this.context = context;
		this.contactArray = contactList.toArray(new Contacts[contactList.size()]);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.contact_list, parent, false);
		TextView name = (TextView) row.findViewById(R.id.contact_name);
		Contacts Contact = contactArray[position];
		name.setText(Contact.getName());
		return row;
	}
}