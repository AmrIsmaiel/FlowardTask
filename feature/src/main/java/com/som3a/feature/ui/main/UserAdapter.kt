package com.som3a.feature.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.som3a.feature.R
import com.som3a.feature.databinding.ItemUserBinding
import com.som3a.feature.model.UserUiModel

class UserAdapter constructor(
    private val onUserSelected: ((UserUiModel?) -> Unit)? = null
) : ListAdapter<UserUiModel, UserAdapter.UserViewHolder>(UserItemDiffUtil()) {

    inner class UserViewHolder(
        private val binding: ItemUserBinding,
        private val onUserSelected: ((UserUiModel?) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userUiModel: UserUiModel) {
            with(binding) {
                userImageView.apply {
                    Glide.with(this)
                        .load(userUiModel.thumbnailUrl)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(android.R.drawable.stat_notify_error)
                        .into(this)
                }
                userTitleTextView.text = userUiModel.name
                postsCountTextView.text = String.format(
                    this.root.resources.getString(R.string.posts_count),
                    userUiModel.posts?.size ?: 0
                )
                this.root.setOnClickListener { onUserSelected?.invoke(userUiModel) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding = binding, onUserSelected = onUserSelected)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserItemDiffUtil : DiffUtil.ItemCallback<UserUiModel>() {
    override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(
        oldItem: UserUiModel,
        newItem: UserUiModel
    ): Boolean {
        return oldItem == newItem
    }
}