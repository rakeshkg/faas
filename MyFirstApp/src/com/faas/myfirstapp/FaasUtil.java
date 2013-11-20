package com.faas.myfirstapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class FaasUtil {
	

	public void getContactsByLastName1(String lastname) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FaasContacts");
		query.whereEqualTo("LastName", lastname);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> contacts, ParseException e) {
				if (e == null) {
					Log.d("contacts", "Retrieved " + contacts.size() + " contacts");
				}else {
					Log.d("Contacts", "Error could not find :" + e.getMessage());
				}
				
			}
		});
	}
	
	public void addContact(HashMap<String, String> contact) {
		try {
			ParseObject testObject = new ParseObject("FaasContacts");
			Log.d("addContact", " Fname = " + contact.get("fname") + " Last name =" + contact.get("lname"));
			
			testObject.put("FirstName", contact.get("fname"));
			testObject.put("LastName", contact.get("lname"));
			testObject.put("Email", contact.get("email"));
			testObject.put("Phone", contact.get("phone"));
			testObject.save();
		} catch (Exception e){
			Log.d("addcontact", "Exception = " + e.getMessage());
			
		}
	}
	
	public List<Object> getContactsByFirstName(String fname) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FaasContacts");
		query.whereEqualTo("FirstName", fname);
		List<ParseObject> contacts = new ArrayList<ParseObject>();
		List<Object> contactObjs = new ArrayList<Object>();
 		try {
			contacts = query.find();
 			contactObjs = new ArrayList<Object>(contacts);
		} catch (ParseException e) {
			Log.d("contactByFirstName", "Error:" + e.getMessage());
		}
		return contactObjs;
		
	}
	
	public List<Object> getContactsByLastName(String lname) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FaasContacts");
		query.whereEqualTo("FirstName", lname);
		List<ParseObject> contacts = new ArrayList<ParseObject>();
		List<Object> contactObjs = new ArrayList<Object>();
 		try {
			contacts = query.find();
 			contactObjs = new ArrayList<Object>(contacts);
		} catch (ParseException e) {
			Log.d("contactByFirstName", "Error:" + e.getMessage());
		}
		return contactObjs;
	}
	
	public List<Object> getContactsByEmail(String email) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FaasContacts");
		query.whereEqualTo("Email", email);
		List<ParseObject> contacts = new ArrayList<ParseObject>();
		List<Object> contactObjs = new ArrayList<Object>();
		try {
			contacts = query.find();
			contactObjs = new ArrayList<Object>(contacts);
		} catch (ParseException e) {
			Log.d("contactsByEmail", "Error: " + e.getMessage());
		}
		return contactObjs;
	}
 	
//	public List<Object> getContactsByPhone(String phone) {
//		ParseQuery<ParseObject> query = ParseQuery.getQuery("FaasContacts");
//	}
	
	
	public void getContactsByEmail1() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FaasContacts");
		query.whereEqualTo("Email", "gupta.rakesh@gmail.com");
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> contacts , ParseException e) {
				if (e == null) {
					Log.d("OnResume", " Found " + contacts.size() + " contacts ");
					for (int itr=0; itr < contacts.size(); itr++) {
						String contactid = contacts.get(itr).getObjectId() ;
						ParseObject contact = contacts.get(itr);
						Log.d("ObjectId" , " Object id = " + contactid);
						if (itr == 1) {
							contact.put("FirstName", contact.getString("FirstName")+ itr);
							contact.put("LastName", contact.getString("LastName") + itr);
							contact.put("Email", contact.getString("Email") + itr);
							contact.put("Phone", contact.getString("Phone") + itr);
							contact.saveInBackground();
							break;
						}
						
					}
				}else {
					Log.d("ObjectId" , "Error :" + e.getMessage());
					
				}
			}
			
		});
	}	

}
