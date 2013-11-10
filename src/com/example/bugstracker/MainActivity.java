package com.example.bugstracker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.example.bugstracker.network.MultipartRequest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
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
					
					MultipartRequest mr = new MultipartRequest("http://revall.co/main/upload", el, l  , f, "resim");
					
					
					
					RequestQueue queue = Volley.newRequestQueue(this);
					
					queue.add(mr);
				
					
			}}
		
		
		

}
