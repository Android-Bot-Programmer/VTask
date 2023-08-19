package ru.vaa.vtask.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.vaa.vtask.data.RegistrationUIState

class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName
    private var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: SignUpUIEvent) {
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
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }
}