package com.som3a.feature.ui.main

import androidx.lifecycle.viewModelScope
import com.som3a.base.BaseViewModel
import com.som3a.common.Resource
import com.som3a.domain.usecase.GetUsersWithPostsUseCase
import com.som3a.feature.mapper.UserDomainUiMapper
import com.som3a.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersWithPostsUseCase: GetUsersWithPostsUseCase,
    private val userDomainUiMapper: UserDomainUiMapper
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            usersState = MainContract.UsersState.Idle,
            selectedUser = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnGetUsersList -> fetchUsersList()
            is MainContract.Event.OnUserSelected -> {
                event.userUiModel
            }
        }
    }

    private fun fetchUsersList() {
        viewModelScope.launch {
            getUsersWithPostsUseCase.execute()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(usersState = MainContract.UsersState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(usersState = MainContract.UsersState.Idle) }
                        }
                        is Resource.Error -> {
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                        is Resource.Success -> {
                            setState {
                                copy(
                                    usersState = MainContract.UsersState.Success(
                                        usersWithPosts = userDomainUiMapper.mapList(it.data)
                                    )
                                )
                            }
                        }
                    }
                }
        }
    }
}