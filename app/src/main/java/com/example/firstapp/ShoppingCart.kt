package com.example.firstapp

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

// ---------- Задание 10 ----------
// Главная ошибка исходного кода: totalSum и productSize были обычными полями активити
// и увеличивались ВНУТРИ composable-функции. Compose перерисовывает экран много раз
// (рекомпозиция), и при каждой перерисовке к сумме снова прибавлялись цены всех товаров.
// Исправление: ничего не накапливаем, а вычисляем из списка products.

data class Cart(
    val products: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

@Composable
fun ShoppingCartScreen() {
    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }

    // ИСПРАВЛЕНО: сумма вычисляется из списка, а не накапливается
    val totalSum = products.sumOf { it.price }

    Column {
        products.forEach { product ->
            Text(text = "${product.name} - ${product.price} рублей")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Товаров на сумму: $totalSum рублей")

        AddProductSection(totalSum = totalSum) {
            products = products + Product(
                id = products.size,
                name = "Товар #${products.size + 1}",
                price = Random.nextInt(0, 100)
            )
        }

        RemoveProductSection(hasProducts = products.isNotEmpty()) {
            products = products.dropLast(1)
        }
    }
}

@Composable
fun AddProductSection(
    totalSum: Int,
    onAdd: () -> Unit
) {
    val context = LocalContext.current
    val isFreeDelivery = totalSum > 500

    // ИСПРАВЛЕНО: Toast вызывался прямо в composable — при каждой перерисовке.
    // LaunchedEffect срабатывает только когда меняется isFreeDelivery.
    LaunchedEffect(isFreeDelivery) {
        if (isFreeDelivery) {
            Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
        }
    }

    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(
    hasProducts: Boolean,          // ИСПРАВЛЕНО: вместо productSize, который никогда не уменьшался
    onRemove: () -> Unit
) {
    if (hasProducts) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShoppingCartPreview() {
    ShoppingCartScreen()
}
