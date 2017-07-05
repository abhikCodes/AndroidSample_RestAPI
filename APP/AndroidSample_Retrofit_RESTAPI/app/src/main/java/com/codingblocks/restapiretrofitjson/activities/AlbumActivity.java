package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.AlbumAdapter;
import com.codingblocks.restapiretrofitjson.api.AlbumsAPI;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.Albums;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumActivity extends AppCompatActivity {

    RecyclerView rvAlbumList;
    AlbumAdapter albumAdapter;
    public static final String TAG = "AlbumActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        rvAlbumList = (RecyclerView) findViewById(R.id.rvAlbumList);
        rvAlbumList.setLayoutManager(new LinearLayoutManager(this));
        albumAdapter = new AlbumAdapter(this , new ArrayList<Albums>());
        rvAlbumList.setAdapter(albumAdapter);

        /*albumAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, Class classActivity) {
                *//*Intent photoActivityIntent = new Intent(AlbumActivity.this , classActivity);
                photoActivityIntent.putExtra("photoInt" , itemId);
                startActivity(photoActivityIntent);*//*
            }
        });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();
        AlbumsAPI albumsAPI = retrofit.create(AlbumsAPI.class);

        Callback<ArrayList<Albums>> albumCallback = new Callback<ArrayList<Albums>>() {

            @Override
            public void onResponse(Call<ArrayList<Albums>> call, Response<ArrayList<Albums>> response) {
                Log.d(TAG, "onResponse: ");
                albumAdapter.updateAlbums(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Albums>> call, Throwable t) {

            }
        };
        int userIdRecieved = getIntent().getIntExtra("postId", -1);
        if(userIdRecieved==-1)
            albumsAPI.getAlbums().enqueue(albumCallback);
    }
}
