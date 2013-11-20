package com.faas.myfirstapp;

import java.util.HashMap;

import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.faas.myfirstapp.MESSAGE";
	public final static String EXTRA_FNAME = "fname";
	public final static String EXTRA_LNAME = "lname";
	public final static String EXTRA_PHONE = "phone";
	public final static String EXTRA_EMAIL = "email";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "e1H6NPk71rrpIARCKBNvk8CqwyHCkyVdhx0PcEzi", "t5OB0ajLqp0TEipFON4yVQ3zyVuEw5692f7KbHFD");
		HashMap<String,String> contact = new HashMap<String, String>();
		contact.put("fname", "Vidhi1");
		contact.put("lname", "Gupta1");
		contact.put("phone","408-515-0990");
		contact.put("email", "vidhi1.gup@yahoo.com");
		FaasUtil faas = new FaasUtil();
		faas.addContact(contact);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	@Override
//	public void onStart(){
//		super.onStart();
//		Log.d("onStart", "I am inside onStart");
//
//		HashMap<String,String> contact = new HashMap<String, String>();
//		contact.put("fname", "Vidhi");
//		contact.put("lname", "Gupta");
//		contact.put("phone","408-515-0990");
//		contact.put("email", "vidhi.gup@yahoo.com");
//		FaasUtil faas = new FaasUtil();
//		faas.addContact(contact);
//	}
	
	public void sendMessage(View view) {
		
		Intent intent =  new Intent(this, DisplayMessageActivity.class);

		EditText fnameText = (EditText) findViewById(R.id.first_name);
		EditText lnameText = (EditText) findViewById(R.id.last_name);
		EditText phoneText = (EditText) findViewById(R.id.phone);
		EditText emailText = (EditText) findViewById(R.id.email);
		
		HashMap<String, String> contact = new HashMap<String, String>();
		
		contact.put(EXTRA_FNAME, fnameText.getText().toString());
		contact.put(EXTRA_LNAME, lnameText.getText().toString());
		contact.put(EXTRA_PHONE, phoneText.getText().toString());
		contact.put(EXTRA_EMAIL, emailText.getText().toString());
		
		FaasUtil faas = new FaasUtil();
		faas.addContact(contact);
		String message = " Contact Created Successfully";
		intent.putExtra(EXTRA_MESSAGE, message);
	
		startActivity(intent);
		
	}

}
