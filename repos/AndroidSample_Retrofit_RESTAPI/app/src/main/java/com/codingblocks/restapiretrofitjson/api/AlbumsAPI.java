package com.codingblocks.restapiretrofitjson.api;

import com.codingblocks.restapiretrofitjson.models.Albums;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhik on 01/07/17.
 */

public interface AlbumsAPI {
    @GET("/albums")
    Call<ArrayList<Albums>> getAlbums();

    @GET("/albums")
    Call<ArrayList<Albums>> getAlbumsByUserId(
        @Query("userId") int userId);
}
