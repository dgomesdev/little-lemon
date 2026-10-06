package com.dgomesdev.littlelemonexercise.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgomesdev.littlelemonexercise.domain.model.User
import com.dgomesdev.littlelemonexercise.domain.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: DataRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserState>(UserState.Empty)
    val user: StateFlow<UserState> = _user.asStateFlow()

    init {
        viewModelScope.launch {
            getUserData()
        }
    }

    suspend fun getUserData() {
        _user.value = UserState.Loading
        try {
            Log.i("ProfileViewModel", "getUserData")
            repository.getUser().collect { user ->
                if (user.isEmpty()) {
                    Log.i("ProfileViewModel", "isEmpty")
                    _user.value = UserState.Empty
                } else {
                    Log.i("ProfileViewModel", "isNotEmpty")
                    _user.value = UserState.Success(user)
                }
            }
        } catch (e: Exception) {
            Log.i("ProfileViewModel", "isError: ${e.message}")
            _user.value = UserState.Empty
        }
    }

    fun logOut() {
        viewModelScope.launch {
            repository.logOut()
            _user.value = UserState.Empty
        }
    }
}

sealed class UserState {
    data object Loading : UserState()
    data class Success(val user: User) : UserState()
    data object Empty : UserState()
}