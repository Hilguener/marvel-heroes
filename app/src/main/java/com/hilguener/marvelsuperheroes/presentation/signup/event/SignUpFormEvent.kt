package com.hilguener.marvelsuperheroes.presentation.signup.event

sealed class SignUpFormEvent{
    data class NameChanged(val name: String) : SignUpFormEvent()
    data class EmailChanged(val email: String) : SignUpFormEvent()
    data class PasswordChanged(val password: String) : SignUpFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpFormEvent()
    data object Submit : SignUpFormEvent()
}