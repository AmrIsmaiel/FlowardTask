package com.som3a.data.repository

import com.som3a.domain.entity.Post
import com.som3a.domain.entity.User

interface RemoteDataSource {
    suspend fun getUsersList(): List<User>
    suspend fun getPostsList(): List<Post>
}