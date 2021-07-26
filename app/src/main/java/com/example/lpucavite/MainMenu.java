package com.example.lpucavite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenu extends AppCompatActivity {

    private ImageView imageView;
    private TextView name;
    private TextView email;
    private ImageButton signOut;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton artcad = (ImageButton)findViewById(R.id.artcad);
        artcad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),orgs.class);
                startActivity(startIntent);
            }
        });

        ImageButton events = (ImageButton)findViewById(R.id.events);
        events.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),EventActivity.class);
                startActivity(startIntent);
            }
        });

        ImageButton news = (ImageButton)findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),NewsActivity.class);
                startActivity(startIntent);
            }
        });

        ImageButton info = (ImageButton)findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),infopop.class);
                startActivity(startIntent);
            }
        });

        ImageView mTextViewShowUploads = findViewById(R.id.gallery);

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageActivity();

            }
        });




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        signOut = findViewById(R.id.button);

        signOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button) {
                    signOut();
                }
            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            email.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);

        }







    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainMenu.this, "Signed out", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    private void openImageActivity() {
        Intent intent = new Intent(this,ImagesActivity.class);
        startActivity(intent);
    }



}
