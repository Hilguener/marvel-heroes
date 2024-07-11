package com.hilguener.marvelsuperheroes.presentation.sign_in

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
    val isSignInSuccessful: Boolean = false,
)
