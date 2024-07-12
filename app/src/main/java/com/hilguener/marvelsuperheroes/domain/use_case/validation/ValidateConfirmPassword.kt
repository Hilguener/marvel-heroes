package com.hilguener.marvelsuperheroes.domain.use_case.validation

class ValidateConfirmPassword {

    fun execute(password: String, confirmPassword: String): ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }
        return ValidationResult(successful = true)
    }
}