package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.PhotoAdapter;
import com.codingblocks.restapiretrofitjson.api.PhotoAPI;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.Photos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView rvPhotoList;
    PhotoAdapter photoAdapter;
    public static final String TAG = "PhotoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Log.d(TAG, "onCreate: ");
        rvPhotoList = (RecyclerView) findViewById(R.id.rvPhotosList);
        rvPhotoList.setLayoutManager(new LinearLayoutManager(this));
        photoAdapter = new PhotoAdapter(this , new ArrayList<Photos>());
        rvPhotoList.setAdapter(photoAdapter);

        /*photoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, Class classActivity) {
                *//*Intent picDIsplayIntent = new Intent(PhotoActivity.this,
                        classActivity);
                Log.d(TAG, "onItemClick: ");

                picDIsplayIntent.putExtra("picxy", itemId);
                startActivity(picDIsplayIntent);*//*

            }

        });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();
        PhotoAPI photoAPI = retrofit.create(PhotoAPI.class);

        Callback<ArrayList<Photos>> photosCallback = new Callback<ArrayList<Photos>>() {

            @Override
            public void onResponse(Call<ArrayList<Photos>> call, Response<ArrayList<Photos>> response) {
                Log.d(TAG, "onResponse: "+response.body().size());
                photoAdapter.updatePhotos(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<Photos>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);

            }
        };
        int albumIdReceived = getIntent().getIntExtra("photoact", -1);
        if (albumIdReceived != -1) {
            photoAPI.getPhotosByAlbumId(albumIdReceived).enqueue(photosCallback);
        } else {

        }
    }
}
