package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.components.CustomHeadingTextComponent
import ru.vaa.vtask.data.signup.SignUpViewModel
import ru.vaa.vtask.ui.theme.Monochrome10

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(Monochrome10)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CustomHeadingTextComponent(value = "Home")
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                onClick = { signUpViewModel.logout() }
            ) {
                Text(text = "Logout")
            }
        }
    }
}
