package ru.vaa.vtask.data.rules

object Validator {
    fun validateFirstName(firstName: String): ValidationResult {
        return ValidationResult(
            firstName.isNotEmpty() && firstName.length >= 6
        )
    }

    fun validateLastName(lastName: String): ValidationResult {
        return ValidationResult(
            lastName.isNotEmpty() && lastName.length >= 4
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return ValidationResult(
            email.matches(emailRegex)
        )
    }

    fun validatePassword(pass: String): ValidationResult {
        return ValidationResult(
            pass.isNotEmpty() && pass.length >= 4
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)