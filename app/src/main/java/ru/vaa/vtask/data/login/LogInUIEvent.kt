package ru.vaa.vtask.data.login

sealed class LogInUIEvent {
    data class EmailChanged(val email: String) : LogInUIEvent()
    data class PasswordChanged(val password: String) : LogInUIEvent()
    object LogInButtonClicked : LogInUIEvent()
}
