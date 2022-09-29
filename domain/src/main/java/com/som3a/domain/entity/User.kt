package com.som3a.domain.entity

data class User(
    val albumId: Int,
    val userId: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    var posts: MutableList<Post>? = mutableListOf()
)
