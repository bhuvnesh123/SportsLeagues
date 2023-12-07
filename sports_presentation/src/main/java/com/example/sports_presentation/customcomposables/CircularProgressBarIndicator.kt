package com.example.sports_presentation.customcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.sports_presentation.common.component.Dimens

@Composable
fun CircularProgressBarIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(Dimens.grid_8)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularProgressBarIndicatorPreview() = CircularProgressBarIndicator()

