 package com.example.cahiapp.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cahiapp.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

 public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {


    GoogleApiClient mGoogleApiClient;

    private SignInButton signInButton;

    //private FirebaseAuth mAuth;
    private static final String TAG = "SignInActivity";
    private static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

    }

        @Override
        public void onClick(View view) {

           switch(view.getId()) {
               case R.id.signInButton:
                   Toast.makeText(this, "SignIn Button clicked", Toast.LENGTH_SHORT);
                   signIn();
                   break;

                }

            }


        private void signIn() {

            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, SIGN_IN_CODE);
        }

    @Override
     public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == SIGN_IN_CODE) {

             //TODO: kijken of we hier misschien Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data); van moeten maken!
             //GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
             GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

             //handleSignInResult(result);

             handleSignInResult(result);


         }
     }

     private void handleSignInResult(GoogleSignInResult signInResult) {

        Log.d(TAG, "handleSignInResult:" + signInResult.isSuccess());
        if (signInResult.isSuccess()) {

            Toast.makeText(this, "Signed in Succesfully!", Toast.LENGTH_LONG).show();
            GoogleSignInAccount account = signInResult.getSignInAccount();
            goMainScreen();

        }
        else {


            Toast.makeText(this, "Helaas, signin Failed! Tuzzz", Toast.LENGTH_LONG).show();
        }



     }
//     private void handleSignInResult(GoogleSignInResult result) {
//        if (result.isSuccess()) {
//
//            goMainScreen();
//
//        } else {
//            Toast.makeText(this, getString(R.string.not_log_in) , Toast.LENGTH_SHORT).show();
//        }
//     }

     private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
     }
 }

