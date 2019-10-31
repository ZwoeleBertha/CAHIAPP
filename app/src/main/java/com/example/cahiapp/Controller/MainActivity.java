package com.example.cahiapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cahiapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private Context context = MainActivity.this;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SignOut button functionaliteit


        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final AddPhotoFragment addPhotoFragment = new AddPhotoFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home) {
                    Intent a = new Intent (MainActivity.this, MainActivity.class);
                    startActivity(a);
                }
                else if (id == R.id.account) {
                    Intent i = new Intent (MainActivity.this, UserProfileActivity.class);
                    startActivity(i);
                }
                return false;
            }
        });

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        loadImageByInternetURL("https://www.nocowboys.co.nz/images/v3/no-image-available.png",imageView1);
        loadImageByInternetURL("https://www.nocowboys.co.nz/images/v3/no-image-available.png",imageView2);
        loadImageByInternetURL("https://www.nocowboys.co.nz/images/v3/no-image-available.png",imageView3);

    }

    private void loadImageByInternetURL(String URL, ImageView imageView){
        Glide
                .with(context)
                .load(URL)
                .into(imageView);

    }


    private void gotoUserProfile() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        //   intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setFragment(Fragment fragment) {

    }
}


