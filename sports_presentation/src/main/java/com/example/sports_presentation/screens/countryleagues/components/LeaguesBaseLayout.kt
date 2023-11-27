package com.example.sports_presentation.screens.countryleagues.components

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sports_presentation.R
import com.example.sports_presentation.customcomposables.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeaguesBaseLayout(countryName: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.country_details_screen_title),
                onBack = onBack
            )
        },
    ) {
        LeaguesScreenContent(countryName = countryName)
    }
}