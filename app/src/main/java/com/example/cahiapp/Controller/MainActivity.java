package com.example.cahiapp.Controller;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.EventLog;
import android.util.Log;
import android.webkit.MimeTypeMap;
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
import com.example.cahiapp.Model.User;
import com.example.cahiapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private TextView sotd;
    private Context context = MainActivity.this;
    private String URL = "https://www.oprolletjes.nl/wp-content/uploads/2018/03/Leger-gaat-het-niet-worden.png";
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private static int SPLASH_TIME_OUT = 4000;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sotd = (TextView)findViewById(R.id.sotd);
        sotd.setText("It is my mother in laws birthday and this is her present!");


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


        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);

        loadImageByInternetURL("https://firebasestorage.googleapis.com/v0/b/cahiapp.appspot.com/o/broom.jpg?alt=media&token=db5af279-35ec-4204-8fb2-2ff26af5cb0f",imageView1);
        loadImageByInternetURL("https://firebasestorage.googleapis.com/v0/b/cahiapp.appspot.com/o/urn.jpg?alt=media&token=e3566560-b62f-4b4e-bb8a-d35c96196d49",imageView2);
        loadImageByInternetURL(URL,imageView3);
        loadImageByInternetURL(URL,imageView4);
        loadImageByInternetURL(URL,imageView5);
        loadImageByInternetURL(URL,imageView6);

    }

    private void loadImageByInternetURL(String URL, ImageView imageView){
        Glide
                .with(context)
                .load(URL)
                .into(imageView);

    }

    private void setFragment(Fragment fragment) {

    }
}


