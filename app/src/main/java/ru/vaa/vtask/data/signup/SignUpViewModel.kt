package ru.vaa.vtask.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.vaa.vtask.data.RegistrationUIState
import ru.vaa.vtask.data.rules.Validator

class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: SignUpUIEvent) {
        //validateDataWithRules()
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignUpUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignUpUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignUpUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_printState")
        printState()

        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val firstNameResult = Validator.validateFirstName(registrationUIState.value.firstName)
        val lastNameResult = Validator.validateLastName(registrationUIState.value.lastName)
        val emailResult = Validator.validateEmail(registrationUIState.value.email)
        val passwordResult = Validator.validatePassword(registrationUIState.value.password)
        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "firstNameResult: $firstNameResult")
        Log.d(TAG, "lastNameResult: $lastNameResult")
        Log.d(TAG, "emailResult: $emailResult")
        Log.d(TAG, "passwordResult: $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }
}