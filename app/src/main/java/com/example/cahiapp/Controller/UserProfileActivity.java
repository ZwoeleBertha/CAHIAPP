package com.example.cahiapp.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cahiapp.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class UserProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    ImageView profileImageView;
    TextView nameTextView, emailTextView;
    Button signOutButton;



    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileImageView = findViewById(R.id.profileImageView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        signOutButton = findViewById(R.id.signOutButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()){

            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }
        else{

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {

                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult);
                }

            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            nameTextView.setText(account.getDisplayName());
            emailTextView.setText(account.getEmail());

            //Profile picture ophalen en verwerken
            Uri userPhoto = account.getPhotoUrl();
            Glide.with(this).load(String.valueOf(userPhoto)).into(profileImageView);

        }
        else{

            Toast.makeText(this, "Sign in Failed, please try again", Toast.LENGTH_LONG).show();
            goLogInScreen();
        }
    }


    private void goLogInScreen() {


        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void logOut(View view) {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {

            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                }
                else{

                    Toast.makeText(getApplicationContext(), "Log out failed", Toast.LENGTH_SHORT).show();
                }

            }




        }
        );

    }





  /*  //SignOut button functionaliteit
        signOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.signOutButton:
                        signOut();
                        break;
                }
            }

        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String userName = user.getDisplayName();
            String userEmail = user.getEmail();
            Uri userPhoto = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            name.setText(userName);
            email.setText(userEmail);

            Glide.with(this).load(String.valueOf(userPhoto)).into(profileImageView);

        }

    }

    private void signOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(UserProfileActivity.this, "Signed Out succesfully!", Toast.LENGTH_SHORT).show();
                        finish();

                    }


                });
    }*/

}
