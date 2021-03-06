package com.example.bugstracker;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

import com.example.bugstracker.app.BugTrackerApp;

public class MainActivity extends Activity {

	private View TakePhotoBtn;

	private ImageView ProductPhoto;

	private Button ReviewBtn;

	private static final int CAMERA_REQUEST = 1888;

	private static File file = null;
	
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

		File dir = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "BilBil");
		// You can't put file-seperator before "BilBil"

		dir.mkdirs();

		file = new File(dir, "test.png");

		Uri imageUri = Uri.fromFile(file);

		cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
				imageUri);


		

        startActivityForResult(cameraIntent, CAMERA_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {


			file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "BilBil" + File.separator + "test.png");

			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			ProductPhoto.setImageBitmap(bitmap);

			ReviewAct.imageFile = file;
		}
	}

}
