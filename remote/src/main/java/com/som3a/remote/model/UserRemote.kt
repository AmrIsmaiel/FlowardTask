package com.som3a.remote.model

data class UserRemote(
    val albumId: Int,
    val userId: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String
)
