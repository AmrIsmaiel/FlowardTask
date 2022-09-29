package com.som3a.domain.usecase

import com.som3a.common.Resource
import com.som3a.domain.entity.User
import com.som3a.domain.qualifiers.IoDispatcher
import com.som3a.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersWithPostsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun execute(): Flow<Resource<List<User>>> {
        return try {
            repository.getUsersListWithPosts().flowOn(dispatcher)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}