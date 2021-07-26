package com.example.lpucavite;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ImageViewHolder> {

    private Context mContext;
    private List<EventUpload> mUploads;
    private EventAdapter.OnItemClickListener4 mListener;

    public EventAdapter(Context context, List<EventUpload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ImageViewHolder holder, int position) {
        EventUpload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.eventDate.setText(uploadCurrent.getDate());
        holder.textDesc.setText(uploadCurrent.getEventDesc());
        Picasso.get().load(uploadCurrent.getImageUrl())
                .placeholder(R.drawable.lpuca2)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewName;
        public ImageView imageView;
        public TextView eventDate;
        public TextView textDesc;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name2);
            imageView = itemView.findViewById(R.id.image_view_upload);
            eventDate = itemView.findViewById(R.id.event_date);
            textDesc = itemView.findViewById(R.id.event_desc2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }




    }

    public interface OnItemClickListener4 {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(EventAdapter.OnItemClickListener4 listener) {
        mListener = listener;


    }
}