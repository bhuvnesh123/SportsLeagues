package com.example.sports_presentation.customcomposables

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class MessageScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenAppStarted_whenMessageScreenWithErrorDisplayed_thenErrorShouldBeVisible()  {
        // Start the app
        composeTestRule.setContent {
            MaterialTheme {
                MessageScreen("Something went wrong.Please try again later")
            }

        }

        composeTestRule.onNodeWithText("Something went wrong.Please try again later").assertExists()
    }
}