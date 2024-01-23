package com.example.sports_presentation.customcomposables

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class CustomTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenAppStarted_whenCustomTopAppBarWithTitleLeaguesDisplayed_thenTitleLeaguesShouldBeVisible() {
        // Start the app
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = LEAGUES,
                    onBack = {},
                )
            }
        }

        composeTestRule.onNodeWithText(text = LEAGUES).assertExists()
    }

    private companion object {
        const val LEAGUES = "Leagues"
    }
}
