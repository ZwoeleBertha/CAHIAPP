package com.example.cahiapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.EventLog;
import android.util.Log;
import android.widget.ImageView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.example.cahiapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.view.Event;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private Context context = MainActivity.this;

    private static int SPLASH_TIME_OUT = 4000;
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
                } else if (id == R.id.addPhoto) {
                    Intent p = new Intent (MainActivity.this, AddPhotoActivity.class);
                    startActivity(p);
                } else if (id == R.id.account) {
                    Intent i = new Intent (MainActivity.this, UserProfileActivity.class);
                    startActivity(i);
                }
                return false;
            }
        });
<<<<<<< Updated upstream

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        loadImageByInternetURL("https://www.westernillinoisrealty.com/img/noimage.png",imageView1);
        loadImageByInternetURL("https://www.westernillinoisrealty.com/img/noimage.png",imageView2);
        loadImageByInternetURL("https://www.westernillinoisrealty.com/img/noimage.png",imageView3);
    }

    private void loadImageByInternetURL(String URL, ImageView imageView){
        Glide
                .with(context)
                .load(URL)
                .into(imageView);
=======
>>>>>>> Stashed changes
    }

    private void setFragment(Fragment fragment) {

    }
}


