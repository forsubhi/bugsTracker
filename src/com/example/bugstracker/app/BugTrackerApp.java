package com.example.bugstracker.app;

import android.app.Application;

public class BugTrackerApp extends Application {

	public static String reviewTitle;
	public static String reviewBody;

	public static BugTrackerApp app ;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
		
	}
}
