package com.example.appbase.Api;


import com.example.appbase.Datos.DatosPosts;
import com.example.appbase.Negocio.Posts;
import com.example.appbase.Negocio.PostsComent;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//import java.util.List;

public interface InterfasRetrofit {

    @GET("posts")
    Call <ArrayList<Posts>> getPosts();

    @GET("comments")
    Call <ArrayList<PostsComent>> getPostsComents(
            @Query("postId") int id);

}
