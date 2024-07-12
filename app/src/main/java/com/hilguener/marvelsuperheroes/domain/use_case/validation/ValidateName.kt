package com.hilguener.marvelsuperheroes.domain.use_case.validation

import android.util.Patterns

class ValidateName {

    fun execute(name: String): ValidationResult {
        if (name.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The name can't be blank"
            )
        }
        return ValidationResult(successful = true)
    }
}