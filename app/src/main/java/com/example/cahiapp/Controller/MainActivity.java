package com.example.cahiapp.Controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cahiapp.R;


public class MainActivity extends AppCompatActivity {

    private EditText emailBox;

    private EditText passwordBox;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




}
