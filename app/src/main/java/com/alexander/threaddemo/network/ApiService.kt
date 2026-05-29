package com.alexander.threaddemo.network

import com.alexander.threaddemo.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    @GET("posts")
    suspend fun getAllPosts(): List<Post>
}