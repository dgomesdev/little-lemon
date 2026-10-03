package com.dgomesdev.littlelemonexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgomesdev.littlelemonexercise.domain.model.User
import com.dgomesdev.littlelemonexercise.domain.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: DataRepository
): ViewModel() {

    private val _user = MutableStateFlow(User.empty())
    val user = _user

    fun logOut() {
        viewModelScope.launch {
            repository.logOut()
        }
    }
    suspend fun getUserData() {
        _user.value = repository.getUser()
    }

    init {
        viewModelScope.launch {
            getUserData()
        }
    }
}