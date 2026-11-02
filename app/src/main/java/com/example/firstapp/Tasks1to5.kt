package com.example.firstapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Задание 1
@Composable
fun Hello(name: String?) {
    Text(text = if (name != null) "Привет, $name!" else "Имя не задано")
}

//Задание 2
@Preview(name = "portrait", showSystemUi = true)
@Preview(
    name = "landscape",
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,orientation=landscape"
)
@Preview(
    name = "round",
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 0xFFFFFF00,
    device = "spec:width=200dp,height=200dp,dpi=320,isRound=true"
)
@Composable
fun HelloPreview() {
    Hello("Android")
}

//Задание 3
@Preview(showBackground = true)
@Composable
fun TextStylesPreview() {
    val text = stringResource(R.string.compose_info)

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // 1. Зелёный, 16sp, курсив, по центру
        Text(
            text = text,
            color = Color.Green,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // 2. Одна строка с многоточием
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // 3. Отступ первой строки, 24sp, чёрный на зелёном, подчёркнутый
        Text(
            text = text,
            style = TextStyle(
                textIndent = TextIndent(firstLine = 48.sp),
                fontSize = 24.sp,
                color = Color.Black,
                background = Color.Green,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}


//Задание 4
@Preview(showSystemUi = true)
@Composable
fun ButtonPreview() {
    Button(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.Gray),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text("Нажми на меня")
    }
}


//Задание 5
@Preview(showSystemUi = true)
@Composable
fun Task5Preview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()        // вся доступная высота
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color(0xFFFF5722)),
            contentAlignment = Alignment.Center
        ) {
            Text("АБ")
        }
    }
}

