package com.example.lpucavite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class imagepopup extends AppCompatActivity {

    private String popName;
    private String popStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepopup);

        TextView popname = findViewById(R.id.popname);
        ImageView popview = findViewById(R.id.imagepop);
        TextView popstamp = findViewById(R.id.popdate);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String selectedItem = extras.getString("EXTRA_TEXT");
        popName = extras.getString("EXTRA_TEXT2");
        popStamp = extras.getString("EXTRA_TEXT3");

        popname.setText(popName);
        popstamp.setText(popStamp);


        Picasso.get().load(selectedItem)
                .placeholder(R.drawable.placeholder2)
                .fit()
                .centerInside()
                .into(popview);
    }
}
