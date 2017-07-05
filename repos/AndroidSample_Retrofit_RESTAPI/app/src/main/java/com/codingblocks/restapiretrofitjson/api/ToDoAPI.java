package com.codingblocks.restapiretrofitjson.api;

/**
 * Created by abhik on 30/06/17.
 */

import com.codingblocks.restapiretrofitjson.models.ToDo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ToDoAPI
{
    @GET("/todos")
    Call<ArrayList<ToDo>> getToDos();

    @GET("/todos")
    Call<ArrayList<ToDo>> getToDosByUserId(
        @Query("userId") int userId
    );
}
