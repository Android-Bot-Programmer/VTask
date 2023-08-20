package ru.vaa.vtask.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vaa.vtask.components.DateUtils.getFutureDates
import ru.vaa.vtask.ui.theme.Monochrome10
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Monochrome90
import ru.vaa.vtask.ui.theme.Primary
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun SingleRowCalendar(
    modifier: Modifier = Modifier,
    selectedDayBackgroundColor: Color = Primary,
    selectedDayTextColor: Color = Monochrome10,
    dayNumTextColor: Color = Monochrome90,
    dayTextColor: Color = Monochrome60,
    onSelectedDayChange: (Date) -> Unit,
) {
    val calendar = Calendar.getInstance(Locale.getDefault())
    var selectedDate by rememberSaveable { mutableStateOf(calendar.time) }
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    val currentDate by rememberSaveable { mutableStateOf(calendar.time) }
    Column(modifier) {

        WeekDaysHeader(selectedDayBackgroundColor = selectedDayBackgroundColor,
            selectedDayTextColor = selectedDayTextColor,
            dayNumTextColor = dayNumTextColor,
            dayTextColor = dayTextColor,
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
fun WeekDaysHeader(
    modifier: Modifier = Modifier,
    selectedDayBackgroundColor: Color,
    selectedDayTextColor: Color,
    dayNumTextColor: Color,
    dayTextColor: Color,
    firstDayDate: Date,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit
) {

    val weekFinalDays = getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (day in weekFinalDays) {
            Card(
                modifier
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
                        color = dayTextColor,
                        textAlign = TextAlign.Center
                    )
                    Text(text = DateUtils.getDayNumber(day),
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 16.sp,
                        color = if (selectedDate == day) selectedDayTextColor else dayNumTextColor,

                        modifier = (if (selectedDate == day) Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .drawBehind {
                                drawCircle(
                                    color = selectedDayBackgroundColor, radius = this.size.height
                                )
                            }
                        else Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp))
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { onSelectDay(day) },
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

@Preview(locale = "ar", showBackground = true)
@Composable
fun SingleRowCalendarARPreview() {
    SingleRowCalendar(modifier = Modifier.fillMaxWidth()) {}
}