package ru.vaa.vtask.data.rules

object Validator {
    fun validateFirstName(firstName: String): ValidationResult {
        return ValidationResult(
            firstName.isNotEmpty() && firstName.length >= 2
        )
    }

    fun validateLastName(lastName: String): ValidationResult {
        return ValidationResult(
            lastName.isNotEmpty() && lastName.length >= 2
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return ValidationResult(
            email.matches(emailRegex)
        )
    }

    fun validatePassword(pass: String): ValidationResult {
        val passwordRegex =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$".toRegex()
        return ValidationResult(
            pass.isNotEmpty() && pass.length >= 4 && pass.matches(passwordRegex)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)