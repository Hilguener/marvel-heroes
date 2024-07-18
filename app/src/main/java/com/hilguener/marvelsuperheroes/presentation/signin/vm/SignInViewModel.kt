package com.hilguener.marvelsuperheroes.presentation.signin.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilguener.marvelsuperheroes.domain.use_case.authentication.AuthRepository
import com.hilguener.marvelsuperheroes.domain.use_case.validation.SignState
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateEmail
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidatePassword
import com.hilguener.marvelsuperheroes.domain.util.Resource
import com.hilguener.marvelsuperheroes.presentation.signin.event.SignInFormEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(SignState())

    private val _validationEvent = Channel<ValidationEvent>()
    val validationEvent = _validationEvent.receiveAsFlow()

    private val _isLoading = mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading.value

    fun onEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is SignInFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            SignInFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            _isLoading.value = true

            authRepository.signIn(state.email, state.password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _validationEvent.send(ValidationEvent.Success("Success"))
                    }

                    is Resource.Error -> {
                        _validationEvent.send(
                            ValidationEvent.Error(
                                result.message ?: "Unknown error"
                            )
                        )
                    }

                    is Resource.Loading -> {}
                }
            }

            _isLoading.value = false
        }
    }

    sealed class ValidationEvent {
        data class Success(val message: String = "Success") : ValidationEvent()
        data class Error(val message: String) : ValidationEvent()
    }
}
