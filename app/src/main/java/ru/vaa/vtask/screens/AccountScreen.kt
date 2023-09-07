package ru.vaa.vtask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.R
import ru.vaa.vtask.components.AccountItem
import ru.vaa.vtask.components.BottomShadow
import ru.vaa.vtask.components.ButtonLogoutComponent
import ru.vaa.vtask.components.ItemBottomAppBar
import ru.vaa.vtask.components.WarningTextComponent
import ru.vaa.vtask.data.account.AccountViewModel
import ru.vaa.vtask.navigation.PostVTaskRouter
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.SystemBackButtonHandler
import ru.vaa.vtask.ui.theme.Monochrome50
import ru.vaa.vtask.ui.theme.Monochrome70
import ru.vaa.vtask.ui.theme.SystemBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(accountViewModel: AccountViewModel = viewModel()) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = SystemBackground,
                contentColor = Monochrome50
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ItemBottomAppBar(
                        title = stringResource(R.string.close),
                        icon = Icons.Default.Close,
                        onClick = { PostVTaskRouter.navigateTo(Screen.MainScreen) }
                    )
                    ItemBottomAppBar(
                        title = stringResource(R.string.save),
                        icon = Icons.Default.Check,
                        onClick = { /* todo("Save data") */ }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(SystemBackground)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.first_avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(94.dp)
                            .clip(CircleShape)
                    )
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 5.dp, bottom = 5.dp)
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(SystemBackground),
                        onClick = { /* Empty */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Monochrome70
                        )
                    }
                }
            }
            BottomShadow(alpha = .2f, height = 15.dp)
            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                AccountItem(
                    title = stringResource(R.string.full_name),
                    value = accountViewModel.fullName.value,
                    editText = stringResource(R.string.change_name),
                    editClick = { /* click onto Change Name */ }
                )

                Spacer(modifier = Modifier.height(15.dp))
                accountViewModel.emailId.value?.let {
                    AccountItem(
                        title = stringResource(R.string.email),
                        value = it,
                        editText = stringResource(R.string.change_e_mail),
                        editClick = { /* click onto Change E-mail */ }
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
                AccountItem(
                    title = stringResource(R.string.password),
                    value = stringResource(R.string.password_hint),
                    editText = stringResource(R.string.change_password),
                    editClick = { /* click onto Change password */ }
                )

                Spacer(modifier = Modifier.height(15.dp))
                WarningTextComponent(stringResource(R.string.don_t_forget_to_save_changings))

                Spacer(modifier = Modifier.height(25.dp))
                ButtonLogoutComponent(
                    textButton = stringResource(R.string.log_out)
                ) { accountViewModel.logout() }
            }
        }
    }

    SystemBackButtonHandler {
        PostVTaskRouter.navigateTo(Screen.MainScreen)
    }
}
