package com.codingblocks.restapiretrofitjson.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.activities.PhotoActivity;
import com.codingblocks.restapiretrofitjson.activities.PicDisplay;
import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.Photos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by championswimmer on 29/06/17.
 */

public class PhotoAdapter
        extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private ArrayList<Photos> photos;
    public static final String TAG = "PhotoAdapter";

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public PhotoAdapter(Context context, ArrayList<Photos>photos) {
        this.context = context;
        this.photos = photos;
    }

    public void updatePhotos(ArrayList<Photos> photos){
        Log.d(TAG, "updatePhotos: ");
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_photos, parent, false);

        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.PhotoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final Photos thisPhotos = photos.get(position);

        holder.tvPhotos.setText(thisPhotos.getTitle());
        Picasso.with(context).load(thisPhotos.getThumbnailUrl()).into(holder.imgView);

        holder.layt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,PicDisplay.class);
                i.putExtra("picxy",thisPhotos.getId());
                i.putExtra("newpic",thisPhotos.getUrl());
                i.putExtra("title",thisPhotos.getTitle());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        TextView tvPhotos;
        ImageView imgView;
        LinearLayout layt;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            tvPhotos = (TextView) itemView.findViewById(R.id.tvImgTitle);
            imgView = (ImageView) itemView.findViewById(R.id.ivPhotos);
            layt = (LinearLayout) itemView.findViewById(R.id.layt);
        }
    }
}
