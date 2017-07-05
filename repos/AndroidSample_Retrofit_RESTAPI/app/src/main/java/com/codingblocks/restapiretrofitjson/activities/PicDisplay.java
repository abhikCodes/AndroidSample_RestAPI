package com.codingblocks.restapiretrofitjson.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.PhotoAdapter;
import com.codingblocks.restapiretrofitjson.api.PhotoAPI;
import com.codingblocks.restapiretrofitjson.api.PostsAPI;
import com.codingblocks.restapiretrofitjson.models.Photos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicDisplay extends AppCompatActivity {

    ImageView img;
    TextView tvImgTitle;
    Context context;
    public static final String TAG = "PicDisplay";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_display);

        img = (ImageView) findViewById(R.id.imgMain);
        tvImgTitle = (TextView) findViewById(R.id.tvMainTitleImg);

        int id = getIntent().getIntExtra("picxy",-1);
        String url = getIntent().getStringExtra("newpic");
        String title = getIntent().getStringExtra("title");
        tvImgTitle.setText(title);
        Picasso.with(context).load(url).into(img );

    }
}
