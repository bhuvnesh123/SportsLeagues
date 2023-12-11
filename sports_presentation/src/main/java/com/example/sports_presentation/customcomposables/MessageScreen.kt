package com.example.sports_presentation.customcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sports_presentation.common.component.Dimens

@Composable
fun MessageScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(all = Dimens.grid_2)
                .wrapContentSize()
                .align(alignment = Alignment.Center),
            style = TextStyle(fontSize = 16.sp)
        )
    }
}

