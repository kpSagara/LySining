package com.example.lpucavite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener3 {

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<NewsUpload> mUploads;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mProgressCircle = findViewById(R.id.progress_circle3);
        mRecyclerView = findViewById(R.id.recycler_view3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mAdapter = new NewsAdapter(NewsActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        // Set the layout manager to your recyclerview
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(NewsActivity.this);

        mStorage = FirebaseStorage.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("news");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    NewsUpload upload = postSnapshot.getValue(NewsUpload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(NewsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });



    }

    @Override
    public void onItemClick(int position) {

        NewsUpload selectedItem = mUploads.get(position);
        String url = selectedItem.getImageUrl();
        String textName = selectedItem.getName();
        String textDate = selectedItem.getDate();
        String textDesc = selectedItem.getNewsDesc();

        openPopup(url,textName, textDate, textDesc);

    }

    public void openPopup(String url, String textName, String textDate, String textDesc) {

        String popupurl = url;
        String popupname = textName;
        String popupdate = textDate;
        String popupdesc = textDesc;




        Intent intent = new Intent(NewsActivity.this, NewsPopUp.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_IMAGE",popupurl);
        extras.putString("EXTRA_NAME",popupname);
        extras.putString("EXTRA_DATE",popupdate);
        extras.putString("EXTRA_DESC",popupdesc);
        intent.putExtras(extras);
        startActivity(intent);


    }

}