package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.R
import ru.vaa.vtask.components.CustomFAB
import ru.vaa.vtask.components.CustomHeadingTextComponent
import ru.vaa.vtask.components.CustomPasswordTextField
import ru.vaa.vtask.components.CustomTextComponent
import ru.vaa.vtask.components.CustomTextField
import ru.vaa.vtask.data.LoaderIntro
import ru.vaa.vtask.data.signup.SignUpViewModel
import ru.vaa.vtask.data.signup.SignUpUIEvent
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.SystemBackButtonHandler
import ru.vaa.vtask.navigation.VTaskRouter

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(Color.White)
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
                image = R.raw.animation_registration
            )
            CustomTextComponent(value = stringResource(id = R.string.hello))
            CustomHeadingTextComponent(value = stringResource(id = R.string.create_a_new_account))
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                label = stringResource(id = R.string.first_name),
                painterResource = painterResource(id = R.drawable.ic_profile)
            ) { signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it)) }
            CustomTextField(
                label = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.ic_profile)
            ) { signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it)) }
            CustomTextField(
                label = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.ic_mail)
            ) { signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it)) }
            CustomPasswordTextField(
                label = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_pass)
            ) { signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it)) }
        }
        CustomFAB(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 20.dp)
                .align(Alignment.BottomCenter)
                .clip(CircleShape)
        ) { signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked) }
    }
    SystemBackButtonHandler {
        VTaskRouter.navigateTo(Screen.BoardScreen)
    }
}