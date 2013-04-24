package com.tut.hyssala.heppaharkka;
//http://developer.android.com/guide/topics/ui/layout/listview.html

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.*;

import android.R.string;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

	private String kanta;
	private String tunnus;
	private String passu;
	Button button;
	
	//private static String url = "http://www.hyssala.fi/testi2.php?user=hanna&format=json";
	private static String url = null;
	TextView logiview = null;
	String logi = "";
	
	
	 
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
        
        logiview = (TextView)findViewById(R.id.TextView5);
        
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
		
		System.out.println("Avataan yhteys: " + kanta + " " + tunnus);
		logi = "Avataan yhteys: " + kanta + " " + tunnus;		
		loginkirjoitus();
		
		
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
    	        String ID = c.getString(TAG_ID);
    	        String HARKKAPAIKKA = c.getString(TAG_HARKKAPAIKKA);
    	        String HARKKAPAIVA = c.getString(TAG_HARKKAPAIVA);
    	        String HARKKAAIKA = c.getString(TAG_HARKKAAIKA);
    	        String RATSASTAJA = c.getString(TAG_RATSASTAJA);
    	        String HEVONEN = c.getString(TAG_HEVONEN);
    	        String VALMENTAJA = c.getString(TAG_VALMENTAJA);
    	        String ILMOITTAUTUNUT = c.getString(TAG_ILMOITTAUTUNUT);
    	        
    	        
    	 
    	        // Phone number is agin JSON Object
//    	        JSONObject phone = c.getJSONObject(TAG_PHONE);
//    	        String mobile = phone.getString(TAG_PHONE_MOBILE);
//    	        String home = phone.getString(TAG_PHONE_HOME);  
    	        
    	        
    	        
    	        logi = ID + " " + HARKKAPAIKKA + " " + HARKKAPAIVA + " " + HARKKAAIKA + " " + RATSASTAJA + " " + HEVONEN + " " + VALMENTAJA + " " + ILMOITTAUTUNUT;
    	        loginkirjoitus();
    	        
    	            	        
    	    }
    	    
    	   
    	    
    	    
    	    
    	} catch (JSONException e) {
    	    e.printStackTrace();
    	    logi = "Tiedon parsiminen ei onnistunut.";
    	    loginkirjoitus();
    	}
    	
    	lisaaKalenteri();
    	
    }
    
    
    
    public void loginkirjoitus(){
    	
    	Calendar a = Calendar.getInstance(); 
    	
    	String logi2 = logiview.getText().toString();
    	logi = logi2 + " " + a.get(Calendar.HOUR) + ":" + a.get(Calendar.MINUTE) +":"+ a.get(Calendar.SECOND) + " " + logi;
    	logiview.setText(logi);
    	
    }
    
    public void lisaaKalenteri(){
    
    	
    	Intent intent = new Intent(Intent.ACTION_INSERT);
    	intent.setType("vnd.android.cursor.item/event");
    	intent.putExtra(Events.TITLE, "Learn Android");
    	intent.putExtra(Events.EVENT_LOCATION, "Home suit home");
    	intent.putExtra(Events.DESCRIPTION, "Download Examples");

    	// Setting dates
    	GregorianCalendar calDate = new GregorianCalendar(2013, 04, 25);
    	intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
    	  calDate.getTimeInMillis());
    	intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
    	  calDate.getTimeInMillis());

    	// Make it a full day event
    	intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

    	// Make it a recurring Event
    	intent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

    	// Making it private and shown as busy
    	intent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);
    	intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY); 
    	
    	
    	
    	
    	
    	
/*    	add an event to calendar
    	ContentValues l_event = new ContentValues();
    	l_event.put("calendar_id", 12345); //m_selectedCalendarId
    	l_event.put("title", "roman10 calendar tutorial test");
    	l_event.put("description", "This is a simple test for calendar api");
    	l_event.put("eventLocation", "@home");
    	l_event.put("dtstart", System.currentTimeMillis());
    	l_event.put("dtend", System.currentTimeMillis() + 1800*1000);
    	
    	l_event.put("allDay", 0);   	//status: 0~ tentative; 1~ confirmed; 2~ canceled
    	l_event.put("eventStatus", 1);	//0~ default; 1~ confidential; 2~ private; 3~ public
    	l_event.put("visibility", 0); 	//0~ opaque, no timing conflict is allowed; 1~ transparency, allow overlap of scheduling
    	l_event.put("transparency", 0);	//0~ false; 1~ true
    	l_event.put("hasAlarm", 1);
    	Uri l_eventUri;
    
    	if (Build.VERSION.SDK_INT >= 8 ) {
    		l_eventUri = Uri.parse("content://com.android.calendar/events");
    	} else {
    		l_eventUri = Uri.parse("content://calendar/events");
    	}

    	Uri l_uri = this.getContentResolver().insert(l_eventUri, l_event);
    	Log.v("++++++test", l_uri.toString());*/ 	
    	
    }
    
    
    
    
	public void addListenerOnButton() {
		 
		button = (Button) findViewById(R.id.lataanappi);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				TextView kantaTextView=(TextView)findViewById(R.id.kanta);
				kanta = kantaTextView.getText().toString();
				
				TextView tunnusTextView=(TextView)findViewById(R.id.tunnus);
				tunnus = tunnusTextView.getText().toString();
				
				System.out.println("Avataan yhteys: " + kanta + " " + tunnus);
				logi = "Avataan yhteys: " + kanta + " " + tunnus;		
				loginkirjoitus();				
												
				url = kanta + "?user=" + tunnus + "&format=json"; //"http://www.hyssala.fi/testi2.php?user=hanna&format=json";
				url = "http://www.hyssala.fi/testi2.php?user=" + tunnus + "&format=json";
				System.out.println(url);
				
				parsiJSON();
					
				
			}
 
		});
 
	}
    
    
    
    
    
    
    
    
    
    
}
