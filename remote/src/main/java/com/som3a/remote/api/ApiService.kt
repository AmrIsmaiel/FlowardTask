package com.som3a.remote.api

import com.som3a.remote.model.PostRemote
import com.som3a.remote.model.UserRemote
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPostsList(): List<PostRemote>

    @GET("users")
    suspend fun getUsersList(): List<UserRemote>
}