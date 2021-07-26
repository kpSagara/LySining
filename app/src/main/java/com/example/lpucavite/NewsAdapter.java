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

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ImageViewHolder> {

    private Context mContext;
    private List<NewsUpload> mUploads;
    private NewsAdapter.OnItemClickListener3 mListener;

    public NewsAdapter(Context context, List<NewsUpload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public NewsAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new NewsAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ImageViewHolder holder, int position) {
        NewsUpload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.newsDate.setText(uploadCurrent.getDate());
        holder.newsDesc.setText(uploadCurrent.getNewsDesc());
        Picasso.get().load(uploadCurrent.getImageUrl())
                .placeholder(R.drawable.lpuca2)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() { return mUploads.size(); }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewName;
        public ImageView imageView;
        public TextView newsDate;
        public TextView newsDesc;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name3);
            imageView = itemView.findViewById(R.id.image_view_upload);
            newsDate = itemView.findViewById(R.id.news_date);
            newsDesc = itemView.findViewById(R.id.event_desc3);

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

    public interface OnItemClickListener3 {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(NewsAdapter.OnItemClickListener3 listener) {
        mListener = listener;


    }
}