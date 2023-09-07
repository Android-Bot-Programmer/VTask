package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.components.BottomShadow
import ru.vaa.vtask.data.home.HomeViewModel
import ru.vaa.vtask.data.utils.SingleRowCalendar
import ru.vaa.vtask.ui.theme.SystemBackground
import java.util.Date

@Preview(showBackground = true)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    var day by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .background(SystemBackground)
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            SingleRowCalendar {
                day = it
            }
        }
        BottomShadow(alpha = .2f, height = 15.dp)
        Spacer(modifier = Modifier.height(20.dp))

    }
}
