package com.example.cahiapp.Controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cahiapp.R;

public class MakePhotoActivity extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int REAQUEST_IMAGE_CAPTURE = 101;
    private ImageView mImageView;
    private Button TakePicture;
    private ImageView imageView;

    public void takePicture(View view) {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(imageTakeIntent, REAQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REAQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_photo);

        //Display Photo
        imageView = findViewById(R.id.imageView);
        TakePicture = findViewById(R.id.ChoosePicture);

        //Handle button action
        TakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for permission runtime
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                }
            }
        });
    }
}
