package com.som3a.remote.mapper

import com.som3a.common.Mapper
import com.som3a.domain.entity.User
import com.som3a.remote.model.UserRemote
import javax.inject.Inject

class UserRemoteDataMapper @Inject constructor() : Mapper<UserRemote, User> {
    override fun map(input: UserRemote): User {
        return User(
            albumId = input.albumId,
            userId = input.userId,
            name = input.name,
            url = input.url,
            thumbnailUrl = input.thumbnailUrl,
            posts = mutableListOf()
        )
    }

    override fun reverseMap(input: User): UserRemote {
        return UserRemote(
            albumId = input.albumId,
            userId = input.userId,
            name = input.name,
            url = input.url,
            thumbnailUrl = input.thumbnailUrl
        )
    }
}