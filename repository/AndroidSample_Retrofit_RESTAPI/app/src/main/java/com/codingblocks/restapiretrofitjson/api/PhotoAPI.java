package com.codingblocks.restapiretrofitjson.api;

import com.codingblocks.restapiretrofitjson.models.Photos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by abhik on 02/07/17.
 */

public interface PhotoAPI {
    @GET("/photos")
    Call<ArrayList<Photos>> getPhotosByAlbumId(
            @Query("albumId") int albumId
    );

    Call<ArrayList<Photos>> getPhotosById(
            @Path("id") int id
    );

}
