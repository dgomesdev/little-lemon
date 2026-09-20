package com.dgomesdev.littlelemonexercise.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {
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
            if (_firstName.value.isBlank() || _firstName.value.length < 2) false
            else _firstName.value.all { it.isLetter() }
        }
    }

    private fun validateLastName() {
        _isLastNameValid.update {
            if (_lastName.value.isBlank() || _lastName.value.length < 2) false
            else _lastName.value.all { it.isLetter() }
        }
    }

    private fun validateEmail() {
        _isEmailValid.update {
            if (_email.value.isBlank()) false
            else Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
        }
    }

    private fun validateForm() {
        _isFormValid.update {
            _isFirstNameValid.value && _isLastNameValid.value && _isEmailValid.value
        }
    }
}