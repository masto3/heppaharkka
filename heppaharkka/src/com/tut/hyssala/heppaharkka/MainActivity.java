package com.tut.hyssala.heppaharkka;
//http://developer.android.com/guide/topics/ui/layout/listview.html

import org.json.*;

import android.R.string;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	private String kanta;
	private String tunnus;
	private String passu;
	Button button;
	
	private static String url = "http://www.hyssala.fi/testi2.php?user=hanna&format=json";
	 
	// JSON Node names
	private static final String TAG_ID = "ID";
	private static final String TAG_HARKKAPAIKKA = "HARKKAPAIKKA";
	private static final String TAG_HARKKAPAIVA = "HARKKAPAIVA";
	private static final String TAG_HARKKAAIKA = "HARKKAAIKA";
	private static final String TAG_RATSASTAJA = "RATSASTAJA";
	private static final String TAG_HEVONEN = "HEVONEN";
	private static final String TAG_VALMENTAJA = "VALMENTAJA";
	private static final String TAG_ILMOITTAUTUNUT = "ILMOITTAUTUNUT";
	
	// contacts JSONArray
	JSONArray contacts = null;
	
	
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        addListenerOnButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
            
    public void LataaTiedot(View view){
		
		TextView kantaTextView=(TextView)findViewById(R.id.kanta);
		kanta = kantaTextView.getText().toString();
		TextView tunnusTextView=(TextView)findViewById(R.id.tunnus);
		tunnus = tunnusTextView.getText().toString();
		TextView passuTextView=(TextView)findViewById(R.id.salasana);
		passu = passuTextView.getText().toString();
		
		System.out.println("Avataan yhteys: " + kanta + " " + tunnus + " " + passu);	
		
	}
    
    public void parsiJSON(){
    	
    	// Creating JSON Parser instance
    	JSONParser jParser = new JSONParser();
    	 
    	// getting JSON string from URL
    	JSONObject json = jParser.getJSONFromUrl(url);
    	 
    	try {
    		
    	    // Getting Array of Contacts
    	    contacts = json.getJSONArray("posts");
    	    System.out.println(contacts);	//tulostaa koko taulukon
    	    
    	    // looping through All Contacts
    	    for(int i = 0; i < contacts.length(); i++){
    	        JSONObject c = contacts.getJSONObject(i);
    	        c = c.getJSONObject("post");
    	        System.out.println(c);		//tulostaa rivin taulukosta
    	        
    	        // Storing each json item in variable
    	        String id = c.getString(TAG_ID);
    	        String name = c.getString(TAG_HARKKAPAIKKA);
    	        String email = c.getString(TAG_HARKKAPAIVA);
    	        String address = c.getString(TAG_HARKKAAIKA);
    	        String gender = c.getString(TAG_RATSASTAJA);
    	 
    	        // Phone number is agin JSON Object
//    	        JSONObject phone = c.getJSONObject(TAG_PHONE);
//    	        String mobile = phone.getString(TAG_PHONE_MOBILE);
//    	        String home = phone.getString(TAG_PHONE_HOME);
    	        
    	           	        
    	        
    	        
    	        
    	 
    	    }
    	} catch (JSONException e) {
    	    e.printStackTrace();
    	}
    	
    	
    	
    }
    
    
	public void addListenerOnButton() {
		 
		button = (Button) findViewById(R.id.lataanappi);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				parsiJSON();
				
			}
 
		});
 
	}
    
    
    
    
    
    
    
    
    
    
}
