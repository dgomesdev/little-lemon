package com.dgomesdev.littlelemonexercise.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgomesdev.littlelemonexercise.domain.model.User
import com.dgomesdev.littlelemonexercise.domain.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val repository: DataRepository
) : ViewModel() {
    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _isFirstNameValid = MutableStateFlow(false)
    val isFirstNameValid = _isFirstNameValid.asStateFlow()

    private val _isLastNameValid = MutableStateFlow(false)
    val isLastNameValid = _isLastNameValid.asStateFlow()

    private val _isEmailValid = MutableStateFlow(false)
    val isEmailValid = _isEmailValid.asStateFlow()

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid = _isFormValid.asStateFlow()

    fun updateFirstName(name: String) {
        _firstName.value = name
        validateFirstName()
        validateForm()
    }

    fun updateLastName(name: String) {
        _lastName.value = name
        validateLastName()
        validateForm()
    }

    fun updateEmail(email: String) {
        _email.value = email
        validateEmail()
        validateForm()
    }

    private fun validateFirstName() {
        _isFirstNameValid.update {
            !(_firstName.value.isBlank() || _firstName.value.length < 2)
                    && _firstName.value.all { it.isLetter() }
        }
    }

    private fun validateLastName() {
        _isLastNameValid.update {
            !(_lastName.value.isBlank() || _lastName.value.length < 2)
                    && _lastName.value.all { it.isLetter() }
        }
    }

    private fun validateEmail() {
        _isEmailValid.update {
            _email.value.isNotBlank()
                    && Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
        }
    }

    private fun validateForm() {
        _isFormValid.update {
            _isFirstNameValid.value && _isLastNameValid.value && _isEmailValid.value
        }
    }

    fun saveUser() {
        if (_isFormValid.value) {
            viewModelScope.launch {
                repository.saveUser(
                    User(
                        firstName = _firstName.value,
                        lastName = _lastName.value,
                        email = _email.value
                    )
                )
            }
        }
    }
}