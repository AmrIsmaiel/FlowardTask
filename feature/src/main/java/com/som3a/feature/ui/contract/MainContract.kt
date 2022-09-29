package com.som3a.feature.ui.contract

import com.som3a.base.UiEffect
import com.som3a.base.UiEvent
import com.som3a.base.UiState
import com.som3a.feature.model.UserUiModel

class MainContract {

    sealed class Event : UiEvent {
        object OnGetUsersList : Event()
        data class OnUserSelected(val userUiModel: UserUiModel?) : Event()
    }

    data class State(
        val usersState: UsersState,
        val selectedUser: UserUiModel? = null
    ): UiState

    sealed class UsersState {
        object Idle : UsersState()
        object Loading : UsersState()
        data class Success(val usersWithPosts: List<UserUiModel>) : UsersState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}