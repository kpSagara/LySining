package com.example.lpucavite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.content.Intent.EXTRA_TEXT;
import static android.content.Intent.EXTRA_TITLE;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener  {

    public static final String EXTRA_TEXT = "com.example.lpuadmin.EXTRA_TEXT";

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private FirebaseStorage mStorage;

    private DatabaseReference mDatabaseRef;

    private ValueEventListener mDBListener;

    private List<Upload> mUploads;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();


        mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);


        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2);





        // Set the layout manager to your recyclerview
        mRecyclerView.setLayoutManager(mLayoutManager);





        mAdapter.setOnItemClickListener(ImagesActivity.this);



        mStorage = FirebaseStorage.getInstance();



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);

                }
                Collections.reverse(mUploads);

                mAdapter.notifyDataSetChanged();



                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(int position) {


        Upload selectedItem = mUploads.get(position);
        String url = selectedItem.getImageUrl();
        String textName = selectedItem.getName();
        Long Ltimestamp = selectedItem.gettimestamp();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Ltimestamp);
        String stimestamp = DateFormat.format("hh:mm a EEEE,MMMM,dd,yyyy ", cal).toString();
        String timestamp = stimestamp;
        openPopup(url,textName,timestamp);





    }

    public void openPopup(String url, String textName, String timestamp) {

        String popupurl = url;
        String popupname = textName;
        String popupstamp = timestamp;




        Intent intent = new Intent(ImagesActivity.this, imagepopup.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_TEXT",popupurl);
        extras.putString("EXTRA_TEXT2",popupname);
        extras.putString("EXTRA_TEXT3",popupstamp);
        intent.putExtras(extras);
        startActivity(intent);


    }

}
