package com.example.cahiapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cahiapp.R;

import static com.bumptech.glide.Glide.with;
import static java.lang.System.load;

public class SentenceOTDActivity extends AppCompatActivity {
    private ImageView imageView3;
    private ImageView imageView5;
    private ImageView imageView6;
    private Context context = SentenceOTDActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentence_of_the_day_page);

        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);

        loadImageByInternetURL();


    }

    private void loadImageByInternetURL(){
        String internetURL = "https://www.nocowboys.co.nz/images/v3/no-image-available.png";

        Glide
                .with(context)
                .load(internetURL)
                .into(imageView3);
    }
}
