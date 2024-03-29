package com.example.sports_presentation.customcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.sports_presentation.common.component.Dimens

@Composable
fun MessageScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(all = Dimens.dimen_16)
                .wrapContentSize()
                .align(alignment = Alignment.Center),
            style = TextStyle(
                fontSize = Dimens.text_medium,
                color = MaterialTheme.colorScheme.onBackground,
            ),
        )
    }
}
