package com.example.main_project;

import android.speech.tts.TextToSpeech;

public class textts {
	public static void speak(String s)
    {
    	
    	if (com.example.main_project.MainActivity.tts!=null) {
	    	  if (s!=null) {
	    	                        if (!com.example.main_project.MainActivity.tts.isSpeaking()) {
	    	  com.example.main_project.MainActivity.tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
	    	  }}
	    	  }	
    	
    }
}
