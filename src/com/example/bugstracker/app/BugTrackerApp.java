package com.example.bugstracker.app;

import java.io.File;

import android.app.Application;

public class BugTrackerApp extends Application {

	public static String reviewTitle;
	public static String reviewBody;

	public static BugTrackerApp app ;
	public  File file;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = this;
		
	}
}
