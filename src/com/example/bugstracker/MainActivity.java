package com.example.bugstracker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.bugstracker.network.MultipartRequest;

public class MainActivity extends Activity {

	private View TakePhotoBtn;

	private ImageView ProductPhoto;

	private Button ReviewBtn;

	private static final int CAMERA_REQUEST = 1888;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TakePhotoBtn  =  findViewById(R.id.TakePhotoBtn);
		ProductPhoto = (ImageView)   findViewById(R.id.ProductPhoto);
		ReviewBtn   =   (Button)	findViewById(R.id.ReviewBtn);
		
		
		TakePhotoBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			TakePhoto();
				
			}
		});
		
		Display display = getWindowManager().getDefaultDisplay(); 
		int width = display.getWidth();  // deprecated
		int height = display.getHeight();  // deprecated
		
		
		
		ProductPhoto.setLayoutParams(new LayoutParams(width, width));
		
		ReviewBtn.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,ReviewAct.class);
			
			startActivity(intent);
				
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
					
					
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

					//you can create a new file name "test.jpg" in sdcard folder.
					File f = new File(Environment.getExternalStorageDirectory()
					                        + File.separator + "test.jpg");
					try {
						f.createNewFile();
						FileOutputStream fo = new FileOutputStream(f);
						fo.write(bytes.toByteArray());

						// remember close de FileOutput
						fo.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//write the bytes in file
					
					
					ReviewAct.ImageFile = f;
					
				
					
			}}
		
		
		

}
