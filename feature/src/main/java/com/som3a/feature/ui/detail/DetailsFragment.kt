package com.som3a.feature.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.som3a.base.BaseFragment
import com.som3a.feature.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val createLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    private val args by navArgs<DetailsFragmentArgs>()
    lateinit var postsAdapter: PostsAdapter
    override fun createView(savedInstanceState: Bundle?) {
        postsAdapter = PostsAdapter()
        binding.rvPosts.adapter = postsAdapter
        postsAdapter.submitList(args.userUiModel.posts)
        Glide.with(this)
            .load(args.userUiModel.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(android.R.drawable.stat_notify_error)
            .into(binding.userPhotoImageVIew)
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }
}