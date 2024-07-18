package com.hilguener.marvelsuperheroes.presentation.signup.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilguener.marvelsuperheroes.domain.use_case.authentication.AuthRepository
import com.hilguener.marvelsuperheroes.domain.use_case.validation.SignState
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateConfirmPassword
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateEmail
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateName
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidatePassword
import com.hilguener.marvelsuperheroes.domain.util.Resource
import com.hilguener.marvelsuperheroes.presentation.signup.event.SignUpFormEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validateName: ValidateName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateConfirmPassword: ValidateConfirmPassword,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(SignState())

    private val _validationEvent = Channel<ValidationEvent>()
    val validationEvent = _validationEvent.receiveAsFlow()


    private val _isLoading = mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading.value

    fun onEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is SignUpFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is SignUpFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }

            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val nameResult = validateName.execute(state.name)
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val confirmPasswordResult =
            validateConfirmPassword.execute(state.password, state.confirmPassword)

        val hasError = listOf(
            nameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = confirmPasswordResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            _isLoading.value = true

            authRepository.signUp(state.email, state.password, state.name).collect { result ->
                when (result) {
                    is Resource.Loading -> {}
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




