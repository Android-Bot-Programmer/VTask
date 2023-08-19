package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.R
import ru.vaa.vtask.components.ClickableTextComponent
import ru.vaa.vtask.components.CustomFAB
import ru.vaa.vtask.components.CustomHeadingTextComponent
import ru.vaa.vtask.components.CustomPasswordTextField
import ru.vaa.vtask.components.CustomProgressBar
import ru.vaa.vtask.components.CustomTextComponent
import ru.vaa.vtask.components.CustomTextField
import ru.vaa.vtask.data.LoaderIntro
import ru.vaa.vtask.data.login.LogInUIEvent
import ru.vaa.vtask.data.login.LogInViewModel
import ru.vaa.vtask.navigation.PostVTaskRouter
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.SystemBackButtonHandler
import ru.vaa.vtask.ui.theme.Monochrome10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(logInViewModel: LogInViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(Monochrome10),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LoaderIntro(
                modifier = Modifier
                    .size(250.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally),
                image = R.raw.animation_welcome
            )
            CustomTextComponent(value = stringResource(id = R.string.hello))
            CustomHeadingTextComponent(value = stringResource(id = R.string.welcome_back))
            CustomTextField(
                label = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.ic_mail),
                errorStatus = logInViewModel.logInUIState.value.emailError
            ) { logInViewModel.onEvent(LogInUIEvent.EmailChanged(it)) }
            CustomPasswordTextField(
                label = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_pass),
                errorStatus = logInViewModel.logInUIState.value.passwordError
            ) { logInViewModel.onEvent(LogInUIEvent.PasswordChanged(it)) }
            ClickableTextComponent(value = stringResource(id = R.string.forgot_pass)) {
                /** Тут должна быть обработка забытого пароля **/
            }
        }

        if (logInViewModel.logInInProgress.value) {
            CustomProgressBar()
        }

        CustomFAB(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 20.dp)
                .align(Alignment.BottomCenter)
                .clip(CircleShape),
            isEnabled = logInViewModel.allValidationsPassed.value
        ) { logInViewModel.onEvent(LogInUIEvent.LogInButtonClicked) }
    }
    SystemBackButtonHandler {
        PostVTaskRouter.navigateTo(Screen.BoardScreen)
    }
}