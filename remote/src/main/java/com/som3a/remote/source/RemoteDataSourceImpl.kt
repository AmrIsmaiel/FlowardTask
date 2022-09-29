package com.som3a.remote.source

import com.som3a.data.repository.RemoteDataSource
import com.som3a.domain.entity.Post
import com.som3a.domain.entity.User
import com.som3a.remote.api.ApiService
import com.som3a.remote.mapper.PostsRemoteDataMapper
import com.som3a.remote.mapper.UserRemoteDataMapper
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val userRemoteDataMapper: UserRemoteDataMapper,
    private val postsRemoteDataMapper: PostsRemoteDataMapper
) : RemoteDataSource {
    override suspend fun getUsersList(): List<User> {
        val networkData = apiService.getUsersList()
        return userRemoteDataMapper.mapList(networkData)
    }

    override suspend fun getPostsList(): List<Post> {
        val networkData = apiService.getPostsList()
        return postsRemoteDataMapper.mapList(networkData)
    }
}