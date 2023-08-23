package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.vtask.components.BottomShadow
import ru.vaa.vtask.data.home.HomeViewModel
import ru.vaa.vtask.ui.theme.SystemBackground

@Preview(showBackground = true)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "August 22, 2023")
                Row {
                    IconButton(onClick = { /** OnClick **/ }) {
                        Icon(imageVector = Icons.Filled.ChevronLeft, contentDescription = null)
                    }
                    IconButton(onClick = { /** OnClick **/ }) {
                        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
                    }
                }
            }
        }
        BottomShadow(alpha = .2f, height = 15.dp)
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
