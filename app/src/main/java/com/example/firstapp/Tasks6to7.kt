package com.example.firstapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Задание 6

// 1. Круг исходного размера в правом верхнем углу, чёрный фон
@Composable
fun CircleTopEnd() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black),
        contentAlignment = Alignment.TopEnd
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null
        )
    }
}

// 2. Синий фон, круг растянут по размеру и перекрашен в фиолетовый
@Composable
fun CircleStretched() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,           // растянуть без сохранения пропорций
            colorFilter = ColorFilter.tint(Color(0xFF8E24AA)) // фиолетовый
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Task6Preview1() = CircleTopEnd()

@Preview(showSystemUi = true)
@Composable
fun Task6Preview2() = CircleStretched()

//Задание 7

data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String
)

val sampleContacts = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий", "Егорович", "Кузякин", null, "Ивановская область, дер. Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43, кв. 290")
)

// Общий блок с данными контакта
@Composable
fun ContactInfo(
    contact: Contact,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = alignment) {
        Text("Имя: ${contact.name}")
        Text("Отчество: ${contact.patronymic}")
        Text("Фамилия: ${contact.surname}")
        Text("Мобильный телефон: ${contact.phone ?: "---"}")
        Text("Адрес: ${contact.address}")
    }
}

// 1. Column: один контакт
@Preview(showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    ContactInfo(sampleContacts[0])
}

// 2. Column: список карточек
@Composable
fun ContactList(contacts: List<Contact>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        contacts.forEach { contact ->
            ContactInfo(
                contact = contact,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp),
                alignment = Alignment.CenterHorizontally
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListPreview() {
    ContactList(sampleContacts)
}

// 3. Row: карточка с картинкой справа (по условию картинка произвольная)
@Composable
fun ContactRow(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactInfo(
            contact = contact,
            modifier = Modifier.weight(1f),   // занимает всё место, кроме картинки
            alignment = Alignment.CenterHorizontally
        )
        Icon(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Избранное",
            tint = Color(0xFFFF5722),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactRowPreview() {
    ContactRow(sampleContacts[0])
}
