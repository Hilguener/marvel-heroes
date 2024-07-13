package com.hilguener.marvelsuperheroes.presentation.signin

sealed class SignInFormEvent{
    data class EmailChanged(val email: String) : SignInFormEvent()
    data class PasswordChanged(val password: String) : SignInFormEvent()
    data object Submit : SignInFormEvent()
}