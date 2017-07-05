package com.codingblocks.restapiretrofitjson.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.CommentAdapter;
import com.codingblocks.restapiretrofitjson.api.CommentsAPI;
import com.codingblocks.restapiretrofitjson.models.Comment;
import com.codingblocks.restapiretrofitjson.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView rvCommentsList;
    CommentAdapter commentAdapter;
    public static final String TAG = "CommentsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        rvCommentsList = (RecyclerView) findViewById(R.id.rvCommentsList);
        rvCommentsList.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this , new ArrayList<Comment>());
        rvCommentsList.setAdapter(commentAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();
        CommentsAPI commentsAPI = retrofit.create(CommentsAPI.class);

        Callback<ArrayList<Comment>> commentCallback = new Callback<ArrayList<Comment>>() {

            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                Log.d(TAG, "onResponse: ");
                commentAdapter.updateComments(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        };
        int userIdReceived = getIntent().getIntExtra("postId", -1);
        if (userIdReceived != -1) {
            commentsAPI.getCommentsByPostId(userIdReceived).enqueue(commentCallback);
        } else {
            commentsAPI.getComments().enqueue(commentCallback);
        }
    }
}
