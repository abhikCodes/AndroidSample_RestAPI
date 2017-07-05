package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.codingblocks.restapiretrofitjson.R;

public class MainActivity extends AppCompatActivity {

    Button btnUser, btnPosts, btnALbums , btnToDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser = (Button) findViewById(R.id.btnUser);
        btnPosts = (Button) findViewById(R.id.btnPost);
        btnALbums = (Button)findViewById(R.id.btnAlbum);
        btnToDos = (Button) findViewById(R.id.btnToDo);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iuser = new Intent(MainActivity.this , UsersActivity.class);
                startActivity(iuser);

            }
        });
        btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iPosts = new Intent(MainActivity.this , PostsActivity.class);
                startActivity(iPosts);

            }
        });
        btnALbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iAlbums = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(iAlbums);

            }
        });
        btnToDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iToDo = new Intent(MainActivity.this, ToDoActivity.class);
                startActivity(iToDo);

            }
        });

    }
}
