package com.example.lpucavite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class orgs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgs);

        ImageButton dancecomp = (ImageButton)findViewById(R.id.dancecomp);
        dancecomp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),dancecomp.class);
                startActivity(startIntent);
            }
        });

        ImageButton harmchor = (ImageButton)findViewById(R.id.harmchor);
        harmchor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),harmchor.class);
                startActivity(startIntent);
            }
        });

        ImageButton mrms = (ImageButton)findViewById(R.id.mrms);
        mrms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),mrandms.class);
                startActivity(startIntent);
            }
        });

        ImageButton tanghalyceo = (ImageButton)findViewById(R.id.tanghalyceo);
        tanghalyceo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),tanghalyce.class);
                startActivity(startIntent);
            }
        });

        ImageButton symphband= (ImageButton)findViewById(R.id.symphband);
        symphband.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Symphband.class);
                startActivity(startIntent);
            }
        });
    }
}
