package com.example.sports_presentation.screens.countieslist.components

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sports_presentation.R
import com.example.sports_presentation.customcomposables.CustomTopAppBar
/**
 * This composable represents the base layout of the screen, which consists of a [Scaffold] to hold the screen content and app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountryListScreenBaseLayout(callback: (countryName: String) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.country_list_screen_title),
                onBack = onBack
            )
        },
    ) {
        CountryListScreenContent(callback)
    }
}
