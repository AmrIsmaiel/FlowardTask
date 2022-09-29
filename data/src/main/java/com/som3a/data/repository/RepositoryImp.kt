package com.som3a.data.repository

import com.som3a.common.Resource
import com.som3a.domain.entity.User
import com.som3a.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getUsersListWithPosts(): Flow<Resource<List<User>>> {
        return flow {
            val users = remoteDataSource.getUsersList()
            val posts = remoteDataSource.getPostsList()
            if (posts.isNotEmpty()) {
                users.map { user ->
                    posts.map { post ->
                        if (post.userId == user.userId) {
                            user.apply { this.posts?.add(post) }
                        }
                    }
                }
            }
            emit(Resource.Success(users))
        }
    }
}