package com.som3a.feature.mapper

import com.som3a.common.Mapper
import com.som3a.domain.entity.User
import com.som3a.feature.model.UserUiModel
import javax.inject.Inject

class UserDomainUiMapper @Inject constructor() : Mapper<User, UserUiModel> {
    override fun map(input: User): UserUiModel {
        return UserUiModel(
            albumId = input.albumId,
            userId = input.userId,
            name = input.name,
            url = input.url,
            thumbnailUrl = input.thumbnailUrl,
            posts = input.posts
        )
    }

    override fun reverseMap(input: UserUiModel): User {
        return User(
            albumId = input.albumId,
            userId = input.userId,
            name = input.name,
            url = input.url,
            thumbnailUrl = input.thumbnailUrl,
            posts = input.posts
        )
    }
}