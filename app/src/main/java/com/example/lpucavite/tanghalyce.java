package com.example.lpucavite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class tanghalyce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanghalyce);

        ImageButton button = findViewById(R.id.fbtl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFacebookPage();
            }
        });

        ImageButton button2 = findViewById(R.id.instatl);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInstagramPage();
            }
        });

        ImageButton button3 = findViewById(R.id.yttl);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToYoutubePage();
            }
        });

    }

    private void goToFacebookPage() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/792135847845617"));
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TanghalangLykeion/"));
            startActivity(intent);
        }

    }

    private void goToInstagramPage() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/tanghalanglykeion"));
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/tanghalanglykeion/"));
            startActivity(intent);
        }

    }

    private void goToYoutubePage() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCDSULINFR2m6V6H8KbrGIew"));
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCDSULINFR2m6V6H8KbrGIew"));
            startActivity(intent);
        }

    }
}
