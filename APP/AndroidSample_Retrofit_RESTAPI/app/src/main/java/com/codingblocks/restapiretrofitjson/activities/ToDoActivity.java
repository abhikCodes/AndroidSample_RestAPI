package com.codingblocks.restapiretrofitjson.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.ToDoAdapter;
import com.codingblocks.restapiretrofitjson.api.ToDoAPI;
import com.codingblocks.restapiretrofitjson.models.ToDo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToDoActivity extends AppCompatActivity {

    RecyclerView rvToDo;
    ToDoAdapter toDoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        rvToDo = (RecyclerView) findViewById(R.id.rvToDoList);
        rvToDo.setLayoutManager(new LinearLayoutManager(this));
        toDoAdapter = new ToDoAdapter(this, new ArrayList<ToDo>());
        rvToDo.setAdapter(toDoAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        ToDoAPI toDoAPI = retrofit.create(ToDoAPI.class);

        Callback<ArrayList<ToDo>> toDoCallback = new Callback<ArrayList<ToDo>>() {
            @Override
            public void onResponse(Call<ArrayList<ToDo>> call, Response<ArrayList<ToDo>> response) {
                toDoAdapter.updateToDos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ToDo>> call, Throwable t) {

            }
        };
        int userIdrecieved = getIntent().getIntExtra("todoid", -1);
        if(userIdrecieved == -1)
            toDoAPI.getToDos().enqueue(toDoCallback);
        else
            toDoAPI.getToDosByUserId(userIdrecieved).enqueue(toDoCallback);



    }
}
