package com.som3a.domain.repository

import com.som3a.common.Resource
import com.som3a.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getUsersListWithPosts(): Flow<Resource<List<User>>>
}