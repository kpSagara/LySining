package com.example.lpucavite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class dancecomp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dancecomp);

        ImageButton button = findViewById(R.id.fbdc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFacebookPage();
            }
        });

        ImageButton button2 = findViewById(R.id.instadc);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInstagramPage();
            }
        });

    }

    private void goToFacebookPage() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/254538098001508"));
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/LpuCaviteDanceCompany/"));
            startActivity(intent);
        }

    }

    private void goToInstagramPage() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/lcdcofficial"));
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/lcdcofficial/"));
            startActivity(intent);
        }

    }
}
