package com.example.sports_presentation.common.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.sports_presentation.common.Dimensions
import com.example.sports_presentation.common.smallDimensions

// Composable function that provides dimensions to its children
@Composable
fun ProvideDimens(
    dimensions: Dimensions, // Dimensions parameter that is passed to children
    content: @Composable () -> Unit // Lambda that returns a composable function
) {
    val dimensionSet = remember { dimensions } // Remember the dimensions parameter
    // Provide the dimensions parameter to the LocalAppDimens key
    CompositionLocalProvider(
        LocalAppDimens provides dimensionSet,
        content = content
    )
}

// LocalComposition that provides default dimensions
private val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions // Default dimensions
}


// Object that defines the app theme
object AppTheme {
    // Property that returns the current value of LocalAppDimens
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}

// Property that is a shortcut to get the current value of LocalAppDimens
val Dimens: Dimensions
    @Composable
    get() = AppTheme.dimens