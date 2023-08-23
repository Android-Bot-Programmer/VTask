package ru.vaa.vtask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.R
import ru.vaa.vtask.components.BottomShadow
import ru.vaa.vtask.components.SettingsItems
import ru.vaa.vtask.data.settings.SettingsViewModel
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Monochrome80
import ru.vaa.vtask.ui.theme.SystemBackground

@Preview(showBackground = true)
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = viewModel()) {

    settingsViewModel.getUserData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SystemBackground)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.first_avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 5.dp)
                ) {
                    Text(
                        text = settingsViewModel.fullName.value,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            color = Monochrome80
                        )
                    )
                    settingsViewModel.emailId.value?.let {
                        Text(text = it, color = Monochrome60)
                    }
                }
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = Monochrome60
                )
            }
        }
        BottomShadow(alpha = .2f, height = 15.dp)
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {
            SettingsItems(title = stringResource(R.string.first_day)) { }
            SettingsItems(title = stringResource(R.string.notifications)) { }
            SettingsItems(title = stringResource(R.string.time_detection)) { }
            SettingsItems(title = stringResource(R.string.my_day_reset_time), isSpacer = false) { }
            Spacer(modifier = Modifier.height(15.dp))
            SettingsItems(title = stringResource(R.string.completed_tasks)) { }
            SettingsItems(title = stringResource(R.string.daily_push)) { }
            SettingsItems(title = stringResource(R.string.folder_order), isSpacer = false) { }
            Spacer(modifier = Modifier.height(15.dp))
            SettingsItems(title = stringResource(R.string.language), isSpacer = false) { }
            Spacer(modifier = Modifier.height(15.dp))
            SettingsItems(title = stringResource(R.string.help), isSpacer = false) { }
        }
    }
}