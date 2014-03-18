package com.example.main_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.mail.pop3.POP3Store;



public class MainActivity extends Activity implements OnClickListener, OnInitListener {
	
	ArrayList<String> text;
	String forTextView;
	static TextToSpeech tts;
	public enum option{
		send,receive
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
      Button  b = (Button) findViewById(R.id.button1);
      b.setOnClickListener(this);
      tts = new TextToSpeech(this,this);  
      textts.speak("Welcome to the app.To send a mail, speak send and to receive the mail, speak receive");
   
         
        	 }    
    
    public void onClick(View v) {	
			
			SpeechRecognitionHelper.run(MainActivity.this);	
							}      
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {		
			case RESULT_OK: {
			
			if(resultCode == RESULT_OK && null != data) {
				
				Toast t = Toast.makeText(getApplicationContext(),
						"Speech Recognized",
						Toast.LENGTH_SHORT);
				t.show();

			text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			}
		
			break;
			}
			
			case RecognizerIntent.RESULT_NO_MATCH:{
				textts.speak("Not able to recognize");
				break;
			}
		}
		
		String s = text.get(0);	
		Log.d(s,s);
		try{
		option op=option.valueOf(s);
		switch(op){
		case send:	Intent intent = new Intent(this, SendMailActivity.class);
					startActivity(intent);
					Log.d("successfully sent","sent");
					break;
		
		default:textts.speak("Not recognized, please speak again");
		}
		
		}
		catch(Exception e){
			
			textts.speak("Speak Again");
		}
		
		
			
		
		
		
	}   

    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onInit(int code) {
		// TODO Auto-generated method stub
	      if (code==TextToSpeech.SUCCESS) {
	    	  tts.setLanguage(Locale.getDefault());


	    	  } else {
	    	  tts = null;
	    	  Toast.makeText(this, "Failed to initialize TTS engine.",
	    	  Toast.LENGTH_SHORT).show();
	    	  }		
	}	

		// TODO Auto-generated method stub
		
	protected void onDestroy() {
	      if (tts!=null) {
	tts.stop();


	tts.shutdown();
	}
	      super.onDestroy();
	}
	
	
	 }
