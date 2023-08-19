package ru.vaa.vtask.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.vaa.vtask.data.rules.Validator
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.PostVTaskRouter

class LogInViewModel : ViewModel() {

    private val TAG = LogInViewModel::class.simpleName
    var logInUIState = mutableStateOf(LogInUIState())
    var allValidationsPassed = mutableStateOf(false)
    var logInInProgress = mutableStateOf(false)

    fun onEvent(event: LogInUIEvent) {
        when (event) {
            is LogInUIEvent.EmailChanged -> {
                logInUIState.value = logInUIState.value.copy(
                    email = event.email
                )
            }

            is LogInUIEvent.PasswordChanged -> {
                logInUIState.value = logInUIState.value.copy(
                    password = event.password
                )
            }

            is LogInUIEvent.LogInButtonClicked -> {
                logIn()
            }
        }
        validateLogInUIDataWithRules()
    }

    private fun validateLogInUIDataWithRules() {
        val emailResult = Validator.validateEmail(logInUIState.value.email)
        val passwordResult = Validator.validatePassword(logInUIState.value.password)

        logInUIState.value = logInUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun logIn() {
        logInInProgress.value = true
        val email = logInUIState.value.email
        val password = logInUIState.value.password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_login_success")
                Log.d(TAG, "${it.isSuccessful}")

                if (it.isSuccessful) {
                    logInInProgress.value = false
                    PostVTaskRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_login_failure")
                it.localizedMessage?.let { it1 -> Log.d(TAG, it1) }

                logInInProgress.value = false
            }

    }
}
