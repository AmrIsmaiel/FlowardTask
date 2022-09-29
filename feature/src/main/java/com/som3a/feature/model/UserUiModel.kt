package com.som3a.feature.model

import android.os.Parcelable
import com.som3a.domain.entity.Post
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUiModel(
    val albumId: Int,
    val userId: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    var posts: MutableList<Post>? = mutableListOf()
) : Parcelable