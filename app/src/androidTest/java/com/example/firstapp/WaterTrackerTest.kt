package com.example.firstapp

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//Задание 9
class WaterTrackerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent { WaterTracker() }
    }

    private fun addWater(times: Int) {
        repeat(times) { composeTestRule.onNodeWithText("+250 мл").performClick() }
    }

    private fun finishDay() {
        composeTestRule.onNodeWithText("Завершить день").performClick()
    }

    // 1. Кнопка добавления воды есть и работает
    @Test
    fun addButton_isDisplayed_andAdds250() {
        composeTestRule.onNodeWithText("+250 мл")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule.onNodeWithTag("waterAmount").assertTextEquals("0 мл")
        addWater(2)
        composeTestRule.onNodeWithTag("waterAmount").assertTextEquals("500 мл")
    }

    // 2. Кнопка «Завершить день» есть и работает
    @Test
    fun finishDayButton_isDisplayed_andResetsWater() {
        composeTestRule.onNodeWithText("Завершить день")
            .assertIsDisplayed()
            .assertHasClickAction()

        addWater(3)
        finishDay()
        composeTestRule.onNodeWithTag("waterAmount").assertTextEquals("0 мл")
    }

    @Test
    fun finishDay_withNorm_increasesStreak() {
        addWater(6)          // 1500 мл
        finishDay()
        composeTestRule.onNodeWithTag("streakText").assertTextEquals("Дней подряд: 1")
    }

    @Test
    fun finishDay_withoutNorm_resetsStreak() {
        addWater(6)
        finishDay()          // streak = 1
        addWater(1)          // 250 мл — норма не выполнена
        finishDay()
        composeTestRule.onNodeWithTag("streakText").assertTextEquals("Дней подряд: 0")
    }

    // 3. Текст с количеством дней выводится
    @Test
    fun streakText_isDisplayed() {
        composeTestRule.onNodeWithTag("streakText")
            .assertIsDisplayed()
            .assertTextEquals("Дней подряд: 0")
    }
}
