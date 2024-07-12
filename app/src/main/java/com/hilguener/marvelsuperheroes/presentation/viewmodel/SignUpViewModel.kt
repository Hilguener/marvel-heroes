package com.hilguener.marvelsuperheroes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilguener.marvelsuperheroes.domain.use_case.validation.FormState
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateConfirmPassword
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateEmail
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateName
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidatePassword
import com.hilguener.marvelsuperheroes.presentation.FormEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validateName: ValidateName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateConfirmPassword: ValidateConfirmPassword
) : ViewModel() {

    var state by mutableStateOf(FormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is FormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is FormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is FormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }

            is FormEvent.Submit -> {
                submitData()
            }

        }

    }

    private fun submitData() {
        val nameResult = validateName.execute(state.name)
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val confirmPasswordResult = validateConfirmPassword.execute(
            state.password,
            state.confirmPassword
        )
        val hasError = listOf(
            nameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any {
            !it.successful
        }
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
            validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
    }
}