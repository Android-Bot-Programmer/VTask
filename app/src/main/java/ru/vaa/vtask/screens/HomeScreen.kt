package ru.vaa.vtask.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.components.BottomShadow
import ru.vaa.vtask.components.CustomTextWithDate
import ru.vaa.vtask.components.SingleRowCalendar
import ru.vaa.vtask.data.home.HomeViewModel
import ru.vaa.vtask.ui.theme.SystemBackground
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val day = remember { mutableStateOf(Date()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = SystemBackground
            )
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            CustomTextWithDate(day = day.value)
            SingleRowCalendar(
                modifier = Modifier
                    .fillMaxWidth(),
                onSelectedDayChange = { date ->
                    day.value = date
                })
            BottomShadow(alpha = .2f, height = 15.dp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                homeViewModel.logout()
            }) {
            Text(text = "logout")
        }
    }
}
