package com.codingblocks.restapiretrofitjson.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.activities.PhotoActivity;
import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.Albums;

import java.util.ArrayList;

/**
 * Created by championswimmer on 29/06/17.
 */

public class AlbumAdapter
        extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private Context context;
    private ArrayList<Albums> albums;
    public static final String TAG="AlbumAdapter";

    private OnItemClickListener onItemClickListener;

    public AlbumAdapter(Context context, ArrayList<Albums> albums) {
        this.context = context;
        this.albums = albums;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateAlbums(ArrayList<Albums> albums){
        this.albums = albums;
        notifyDataSetChanged();
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_album, parent, false);

        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        final Albums thisAlbum = albums.get(position);

        holder.tvAlbum.setText(thisAlbum.getTitle());
        holder.laytAlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent i=new Intent(context,PhotoActivity.class);
                i.putExtra("photoact",thisAlbum.getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        TextView tvAlbum;
        LinearLayout laytAlb;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            tvAlbum = (TextView) itemView.findViewById(R.id.tvAlbum);
            laytAlb = (LinearLayout) itemView.findViewById(R.id.laytAlb);
        }
    }
}
