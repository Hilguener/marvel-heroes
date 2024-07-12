package com.hilguener.marvelsuperheroes.presentation

sealed class FormEvent{
    data class NameChanged(val name: String) : FormEvent()
    data class EmailChanged(val email: String) : FormEvent()
    data class PasswordChanged(val password: String) : FormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : FormEvent()
    data object Submit : FormEvent()
}