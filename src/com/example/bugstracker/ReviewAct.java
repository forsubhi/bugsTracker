package com.example.bugstracker;

import java.io.File;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.example.bugstracker.app.BugTrackerApp;
import com.example.bugstracker.network.MultipartRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReviewAct extends Activity {

	public static File ImageFile;
	private EditText ReviewTitle;
	private EditText ReviewBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		
		   ReviewTitle  =    (EditText) findViewById(R.id.ReviewTitle);
		 ReviewBody =     (EditText) findViewById(R.id.ReviewBody);
	     Button SendReview  = 	(Button) findViewById(R.id.SendReview);
	     
	     SendReview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SendImageAndReview();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
		return true;
	}
	
	void SendImageAndReview()
	{
		
		
		ErrorListener el = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				VolleyError x = arg0 ; 
				x.toString();
				
			}
		};
		
		Listener<String> l  = new Listener<String>() {

			@Override
			public void onResponse(String arg0) {


				
				String x = arg0;
				
				x.toString();
				
			}
		};
		
		String reviewTitle = ReviewTitle.getText().toString();
		
		String reviewBody = ReviewBody.getText().toString();
		
		BugTrackerApp.reviewTitle  =reviewTitle ; 
		
		BugTrackerApp.reviewBody=reviewBody;
		
		MultipartRequest mr = new MultipartRequest("http://revall.co/main/upload", el, l  , ImageFile, "resim");
		
		
		
		RequestQueue queue = Volley.newRequestQueue(this);
		
		queue.add(mr);
	
		
	}

}
