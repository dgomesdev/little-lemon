package com.dgomesdev.littlelemonexercise.domain.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
) {
    companion object {
        fun empty(): User {
            return User("", "", "")
        }
    }

    fun isEmpty() =
        firstName.isBlank() && lastName.isBlank() && email.isBlank()
}