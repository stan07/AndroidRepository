package com.example.cabertef.yus.act01;

public class Contacts {
	int id, conid;
	String name, num;
	
	public Contacts() {
		
	}
	
	public Contacts(int id, String name, String num) {
		this.conid = id;
		this.name = name;
		this.num = num;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getConID() {
		return conid;
	}
	
	public void setConID(int conid) {
		this.conid = conid;
	}
	
	public String getNumber() {
		return num;
	}
	
	public void setNumber(String num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
