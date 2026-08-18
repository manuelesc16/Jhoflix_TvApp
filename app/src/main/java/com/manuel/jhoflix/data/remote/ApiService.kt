package com.manuel.jhoflix.data.remote

import com.manuel.jhoflix.data.model.Video
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/videos")
    suspend fun getVideos(): List<Video>

    @GET("api/videos/{id}")
    suspend fun getVideoById(@Path("id") id: String): Video
}
