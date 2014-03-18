package com.example.main_project;

import java.util.ArrayList;
import java.util.Locale;

import com.example.main_project.MainActivity.option;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendMailActivity extends Activity{
	ArrayList<String> text;
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	     send();	     
	         
	        	 }   
	
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

		public void onInit(int code) {
			// TODO Auto-generated method stub
		      if (code==TextToSpeech.SUCCESS) {
		    	  com.example.main_project.MainActivity.tts.setLanguage(Locale.getDefault());


		    	  } else {
		    	  com.example.main_project.MainActivity.tts = null;
		    	  Toast.makeText(this, "Failed to initialize TTS engine.",
		    	  Toast.LENGTH_SHORT).show();
		    	  }		
		}	

			// TODO Auto-generated method stub
			
		protected void onDestroy() {
		      if (com.example.main_project.MainActivity.tts!=null) {
		com.example.main_project.MainActivity.tts.stop();


		com.example.main_project.MainActivity.tts.shutdown();
		}
		      super.onDestroy();
		} 
	 

	public void send() {
		Log.d("entered send method","send method");
		textts.speak("What do you wanna send?");
		Log.d("spoken","spoken");
		SpeechRecognitionHelper.run(SendMailActivity.this);
		
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
			}

			String s = text.get(0);	
			Log.d("going to email the message","entered");

			new ThreadClass().execute(s);
			finish();
			
			
			
		}   
	

}

