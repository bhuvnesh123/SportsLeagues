package com.example.sports_presentation.common

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
This code defines a class `Dimensions` with various properties that represent different sizes in `Dp` and `Sp` units.

The `dimen_*` properties represent dimensions that are based on a grid system. These dimensions are typically used for spacing and sizing of UI elements, such as margins, padding, and icon sizes. The grid system helps ensure that these elements are aligned and spaced consistently across different parts of your UI.

The `plane_*` properties, on the other hand, represent dimensions that are not based on the grid system. These dimensions are typically used for other UI elements that do not need to be aligned with the grid, such as stroke widths, and elevation values.

The `minimum_touch_target`, `text_small`, `text_medium`, and `text_large` properties represent different sizes for touch targets and text.

Two instances of the `Dimensions` class are then created: `smallDimensions` and `sw360Dimensions`. `smallDimensions` uses smaller sizes for all properties compared to `sw360Dimensions`, which uses larger sizes.
 */
class Dimensions(
    val dimen_2: Dp,
    val dimen_4: Dp,
    val diment_8: Dp,
    val dimen_12: Dp,
    val dimen_16: Dp,
    val dimen_20: Dp,
    val dimen_24: Dp,
    val dimen_28: Dp,
    val dimen_32: Dp,
    val dimen_36: Dp,
    val dimen_40: Dp,
    val dimen_44: Dp,
    val dimen_48: Dp,
    val dimen_52: Dp,
    val dimen_56: Dp,
    val dimen_60: Dp,
    val dimen_64: Dp,
    val plane_0: Dp,
    val plane_1: Dp,
    val plane_2: Dp,
    val plane_4: Dp,
    val plane_8: Dp,
    val plane_16: Dp,
    val minimum_touch_target: Dp = 48.dp,
    val text_small: TextUnit,
    val text_medium: TextUnit,
    val text_large: TextUnit,
)

val smallDimensions = Dimensions(
    dimen_2 = 2.dp,
    dimen_4 = 4.dp,
    diment_8 = 6.dp,
    dimen_12 = 10.dp,
    dimen_16 = 12.dp,
    dimen_20 = 15.dp,
    dimen_24 = 18.dp,
    dimen_28 = 22.dp,
    dimen_32 = 24.dp,
    dimen_36 = 28.dp,
    dimen_40 = 30.dp,
    dimen_44 = 34.dp,
    dimen_48 = 36.dp,
    dimen_52 = 40.dp,
    dimen_56 = 42.dp,
    dimen_60 = 46.dp,
    dimen_64 = 52.dp,
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_4 = 4.dp,
    plane_8 = 6.dp,
    plane_16 = 12.dp,
    text_small = 12.sp,
    text_medium = 14.sp,
    text_large = 16.sp,
)

val sw360Dimensions = Dimensions(
    dimen_2 = 2.dp,
    dimen_4 = 4.dp,
    diment_8 = 8.dp,
    dimen_12 = 12.dp,
    dimen_16 = 16.dp,
    dimen_20 = 20.dp,
    dimen_24 = 24.dp,
    dimen_28 = 28.dp,
    dimen_32 = 32.dp,
    dimen_36 = 36.dp,
    dimen_40 = 40.dp,
    dimen_44 = 44.dp,
    dimen_48 = 48.dp,
    dimen_52 = 52.dp,
    dimen_56 = 56.dp,
    dimen_60 = 60.dp,
    dimen_64 = 64.dp,
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_4 = 4.dp,
    plane_8 = 8.dp,
    plane_16 = 16.dp,
    text_small = 14.sp,
    text_medium = 16.sp,
    text_large = 18.sp,
)
