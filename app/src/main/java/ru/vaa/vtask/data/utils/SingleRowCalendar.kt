package ru.vaa.vtask.data.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vaa.vtask.data.utils.DateUtils.getFutureDates
import ru.vaa.vtask.ui.theme.Monochrome10
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Monochrome80
import ru.vaa.vtask.ui.theme.Primary
import ru.vaa.vtask.ui.theme.SystemBackground
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun SingleRowCalendar(onSelectedDayChange: (Date) -> Unit) {
    val calendar = Calendar.getInstance(Locale.getDefault())
    var selectedDate by rememberSaveable { mutableStateOf(calendar.time) }
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    var currentDate by rememberSaveable { mutableStateOf(calendar.time) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SystemBackground)
    ) {

        WeekHeader(
            firstDayDate = currentDate,
            selectedDate = selectedDate,
            onNextWeekClicked = {
                calendar.time = it
                currentDate = it
            }
        ) {
            calendar.time = it
            currentDate = it
        }

        WeekDaysHeader(
            firstDayDate = currentDate,
            selectedDate = selectedDate,
            onSelectDay = { day ->
                calendar.time = day
                selectedDate = day
                onSelectedDayChange(day)
            })
    }
}

@Composable
fun WeekHeader(
    firstDayDate: Date,
    selectedDate: Date,
    onNextWeekClicked: (firstDayDate: Date) -> Unit,
    onPrevWeekClicked: (firstDayDate: Date) -> Unit,
) {

    val dayName = DateUtils.getDayNumber(selectedDate)
    val monthName = DateUtils.getMonthName(selectedDate)
    val yearName = DateUtils.getYear(selectedDate)
    val weekFinalDays = getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val weekFinalDate = weekFinalDays.last()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$monthName $dayName, $yearName",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                color = Monochrome80
            )
        )
        Row {
            IconButton(onClick = {
                val c = Calendar.getInstance()
                c.time = firstDayDate
                c.add(Calendar.DATE, -7)
                val prevWeekFirstDay = c.time
                onPrevWeekClicked(prevWeekFirstDay)
            }) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = null,
                    tint = Monochrome80
                )
            }
            IconButton(onClick = {
                val c = Calendar.getInstance()
                c.time = weekFinalDate
                c.add(Calendar.DATE, 1)
                val nextWeekFirstDay = c.time
                onNextWeekClicked(nextWeekFirstDay)
            }) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Monochrome80
                )
            }
        }
    }
}

@Composable
fun WeekDaysHeader(
    firstDayDate: Date,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit
) {

    val weekFinalDays = getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (day in weekFinalDays) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Monochrome10
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = DateUtils.getDay3LettersName(day),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Monochrome60,
                        textAlign = TextAlign.Center
                    )
                    Text(text = DateUtils.getDayNumber(day),
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 16.sp,
                        color = if (selectedDate == day) Monochrome10 else Monochrome80,

                        modifier = (if (selectedDate == day) Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .drawBehind {
                                drawCircle(
                                    color = Primary, radius = this.size.height
                                )
                            }
                        else Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)).clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onSelectDay(day)
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleRowCalendarPreview() {
    SingleRowCalendar {}
}
