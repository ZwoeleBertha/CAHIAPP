package com.example.cahiapp.Model;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cahiapp.R;

public class Photo extends AppCompatActivity {

    private ImageView mImageView;
    private Button mChooseBtn;
    private static final int REAQUEST_IMAGE_CAPTURE = 101;
    private String photoName;
    private Enumerations photoType;
    private long photoSize;
    private static long maxPhotoSize = 0;
    private static int standardHeight = 0;
    private static int standardWidth = 0;
    int[] standardSize = new int[]{standardWidth,standardHeight};
    private String fileLocation;

    public Photo(String PhotoName, Enumerations photoType, long photoSize , String fileLocation){
        this.photoName = photoName;
        this.photoType = photoType;
        this.photoSize = photoSize;
        this.fileLocation = fileLocation;
    }

    public Photo getPhoto(){
      return null;

    }

    public Ranking getPhotoRanking(){
        return null;
    }

    public Enumerations getPhotoType() {
        return photoType;
    }

    public long getMaxPhotoSize(){
        return 0;
    }

    public void getStandardSize() {
        // TODO: 8-10-2019 return a standard size ;
    }

    public String getFileLocation(){
        return fileLocation;
    }

    public void downloadPhoto(String fileLocation){
        // TODO: 8-10-2019 fill in the function
    }

    public void sharePhoto(Photo photo){
        // TODO: 8-10-2019 fill in the function
    }

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    public void takePicture(View view)
    {
        Intent imageTakeIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

        if(imageTakeIntent.resolveActivity(getPackageManager())!=null)
        {
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
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity_main);

        //Display Photo
        mImageView = findViewById(R.id.imageView);
        mChooseBtn = findViewById(R.id.choose_image_btn);

        //Handle button action
        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for permission runtime
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else {
                //Android is lower than Android Pie
                pickImageFromGallery();
                }


            }
        });

    }



    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {

                }
                else {
                    //permission was denied
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}






