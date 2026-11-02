package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAppTheme {
                // Задание 1: первый запуск — с именем, второй — с null
                // Hello("Тимофей")
                //Hello(null)
                //WaterTracker()
                //ShoppingCartScreen()
            }
        }
    }
}
