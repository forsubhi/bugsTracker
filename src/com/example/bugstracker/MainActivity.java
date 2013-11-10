package com.example.bugstracker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.example.bugstracker.app.BugTrackerApp;
import com.example.bugstracker.network.MultipartRequest;

public class MainActivity extends Activity {

	private View TakePhotoBtn;

	private ImageView ProductPhoto;

	private Button ReviewBtn;

	public static File file;

	private static final int CAMERA_REQUEST = 1888;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TakePhotoBtn = findViewById(R.id.TakePhotoBtn);
		ProductPhoto = (ImageView) findViewById(R.id.ProductPhoto);
		ReviewBtn = (Button) findViewById(R.id.ReviewBtn);

		TakePhotoBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TakePhoto();

			}
		});

		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth(); // deprecated
		int height = display.getHeight(); // deprecated

		ProductPhoto.setLayoutParams(new LayoutParams(width, width));

		ReviewBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ReviewAct.class);

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

		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "test.png");
		Uri imageUri = Uri.fromFile(file);

		cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
				imageUri);
		BugTrackerApp.app.file =file ; 
		
		startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

			// this is small image (thumbnail)
		//	Bitmap photo = (Bitmap) data.getExtras().get("data");

			// read image from file
			Bitmap bitmap = BitmapFactory.decodeFile(BugTrackerApp.app.file.getAbsolutePath());
			ProductPhoto.setImageBitmap(bitmap);
			ReviewAct.ImageFile = file;

		}
	}

}
