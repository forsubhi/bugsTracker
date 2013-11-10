package com.example.bugstracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private View TakePhotoBtn;

	private ImageView ProductPhoto;

	private static final int CAMERA_REQUEST = 1888;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TakePhotoBtn  =  findViewById(R.id.TakePhotoBtn);
		ProductPhoto = (ImageView)   findViewById(R.id.ProductPhoto);
		
		
		TakePhotoBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			TakePhoto();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	void TakePhoto() {

		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
			if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
				
					Bitmap photo = (Bitmap) data.getExtras().get("data");
					
					ProductPhoto.setImageBitmap(photo);
			}}
		
		
		

}
