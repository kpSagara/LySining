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

public class EventActivity extends AppCompatActivity  implements EventAdapter.OnItemClickListener4{

    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<EventUpload> mUploads;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mProgressCircle = findViewById(R.id.progress_circle2);
        mRecyclerView = findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mAdapter = new EventAdapter(EventActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        // Set the layout manager to your recyclerview
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(EventActivity.this);

        mStorage = FirebaseStorage.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("events");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    EventUpload upload = postSnapshot.getValue(EventUpload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EventActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });


    }

    @Override
    public void onItemClick(int position) {

        EventUpload selectedItem = mUploads.get(position);
        String url = selectedItem.getImageUrl();
        String textName = selectedItem.getName();
        String textDate = selectedItem.getDate();
        String textDesc = selectedItem.getEventDesc();

        openPopup(url,textName, textDate, textDesc);

    }

    public void openPopup(String url, String textName, String textDate, String textEventDesc) {

        String popupurl = url;
        String popupname = textName;
        String popupdate = textDate;
        String popupdesc = textEventDesc;




        Intent intent = new Intent(EventActivity.this, eventpopup.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_EVENTIMAGE",popupurl);
        extras.putString("EXTRA_EVENTNAME",popupname);
        extras.putString("EXTRA_EVENTDATE",popupdate);
        extras.putString("EXTRA_EVENTDESC",popupdesc);
        intent.putExtras(extras);
        startActivity(intent);


    }

}