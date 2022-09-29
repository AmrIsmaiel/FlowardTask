package com.som3a.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.som3a.base.BaseFragment
import com.som3a.common.haveNetworkConnection
import com.som3a.feature.R
import com.som3a.feature.databinding.FragmentMainBinding
import com.som3a.feature.ui.contract.MainContract
import com.som3a.feature.ui.detail.DetailsFragment
import com.som3a.feature.ui.detail.DetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override val createLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private val adapter: UserAdapter by lazy {
        UserAdapter { userItem ->
            viewModel.setEvent(
                MainContract.Event.OnUserSelected(
                    userItem
                )
            )
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(userItem!!)
            findNavController().navigate(action)
        }
    }

    override fun createView(savedInstanceState: Bundle?) {
        initObservers()
        binding.rvUsers.adapter = adapter
        getUsersList()
        binding.tryAgainButton.setOnClickListener { getUsersList() }
    }

    private fun getUsersList() =
        viewModel.setEvent(MainContract.Event.OnGetUsersList)

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collect {
                    when (val state = it.usersState) {
                        is MainContract.UsersState.Idle -> {
                            binding.emptyState.isVisible = false
                            binding.progressBar.isVisible = false
                        }
                        is MainContract.UsersState.Loading -> {
                            binding.emptyState.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        is MainContract.UsersState.Success -> {
                            val data = state.usersWithPosts
                            adapter.submitList(data)
                            binding.emptyState.isVisible = data.isEmpty()
                            binding.progressBar.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            binding.emptyState.isVisible = true
                            if (!requireContext().haveNetworkConnection()) {
                                Toast.makeText(
                                    requireContext(),
                                    "No internet connection, please try again later",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            }
        }
    }
}