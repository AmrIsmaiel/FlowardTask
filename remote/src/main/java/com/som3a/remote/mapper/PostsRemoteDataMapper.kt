package com.som3a.remote.mapper

import com.som3a.common.Mapper
import com.som3a.domain.entity.Post
import com.som3a.remote.model.PostRemote
import javax.inject.Inject

class PostsRemoteDataMapper @Inject constructor() : Mapper<PostRemote, Post> {
    override fun map(input: PostRemote): Post {
        return Post(
            id = input.id,
            userId = input.userId,
            title = input.title,
            body = input.body
        )
    }

    override fun reverseMap(input: Post): PostRemote {
        return PostRemote(
            id = input.id,
            userId = input.userId,
            title = input.title,
            body = input.body
        )
    }
}