package com.example.sports_presentation.screens.countieslist.components

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
fun CountryListScreenBaseLayout(callback: (countryName: String) -> Unit) {
    Scaffold(
        topBar = { CustomTopAppBar(title = stringResource(id = R.string.country_list_screen_title)) },
    ) {
        CountryListScreenContent(callback)
    }
}
