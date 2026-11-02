package com.example.firstapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Задание 8

private val primaryColor = Color(0xFF3F5F90)
private val secondaryColor = Color(0xFF555F71)
private val buttonTextColor = Color.White

@Composable
fun WaterTracker() {
    // был пропущен remember
    var waterCount by remember { mutableIntStateOf(0) }
    // дни подряд, когда выпито 1500 мл и больше
    var streakDays by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,   // как на скриншоте
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",                 //  не было text
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor,
            modifier = Modifier.testTag("waterAmount")
        )

        Spacer(modifier = Modifier.height(16.dp))    //  не было значения

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += 250 }          //  было = 250
        ) {
            Text("+250 мл", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = buttonTextColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  кнопка завершения дня
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                streakDays = if (waterCount >= 1500) streakDays + 1 else 0
                waterCount = 0
            }
        ) {
            Text("Завершить день", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = buttonTextColor)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // дни подряд с нормой
        Text(
            text = "Дней подряд: $streakDays",
            fontSize = 20.sp,
            color = secondaryColor,
            modifier = Modifier.testTag("streakText")
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}
