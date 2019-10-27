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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

 public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);

            }
        });

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
             Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

             //handleSignInResult(result);

             handleSignInResult(task);


         }
     }

     private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //When signed in succesfully, go to main page

            goMainScreen();

        }
        catch (ApiException e) {

            Toast.makeText(this, getString(R.string.not_log_in) , Toast.LENGTH_SHORT).show();
            Log.v("Error", "SignInResult:failed code=" + e.getStatusCode());
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

