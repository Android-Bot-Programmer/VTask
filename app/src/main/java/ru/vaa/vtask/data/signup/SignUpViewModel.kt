package ru.vaa.vtask.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.vaa.vtask.data.rules.Validator
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.PostVTaskRouter

class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName
    var signUpUIState = mutableStateOf(SignUpUIState())
    var allValidationsPassed = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
            }

            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside_printState")
        createUserInFirebase(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val firstNameResult = Validator.validateFirstName(signUpUIState.value.firstName)
        val lastNameResult = Validator.validateLastName(signUpUIState.value.lastName)
        val emailResult = Validator.validateEmail(signUpUIState.value.email)
        val passwordResult = Validator.validatePassword(signUpUIState.value.password)
        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "firstNameResult: $firstNameResult")
        Log.d(TAG, "lastNameResult: $lastNameResult")
        Log.d(TAG, "emailResult: $emailResult")
        Log.d(TAG, "passwordResult: $passwordResult")

        signUpUIState.value = signUpUIState.value.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = firstNameResult.status &&
                lastNameResult.status &&
                emailResult.status &&
                passwordResult.status
    }

    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false
                if (it.isSuccessful) {
                    PostVTaskRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")

                signUpInProgress.value = false
            }
    }
}