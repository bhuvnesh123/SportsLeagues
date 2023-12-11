package com.example.sports_presentation.common

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
This code defines a class `Dimensions` with various properties that represent different sizes in `Dp` and `Sp` units.

The `grid_*` properties represent dimensions that are based on a grid system. These dimensions are typically used for spacing and sizing of UI elements, such as margins, padding, and icon sizes. The grid system helps ensure that these elements are aligned and spaced consistently across different parts of your UI.

The `plane_*` properties, on the other hand, represent dimensions that are not based on the grid system. These dimensions are typically used for other UI elements that do not need to be aligned with the grid, such as stroke widths, and elevation values.

The `minimum_touch_target`, `text_small`, `text_medium`, and `text_large` properties represent different sizes for touch targets and text.

Two instances of the `Dimensions` class are then created: `smallDimensions` and `sw360Dimensions`. `smallDimensions` uses smaller sizes for all properties compared to `sw360Dimensions`, which uses larger sizes.
 */
class Dimensions(
    val grid_0_25: Dp,
    val grid_0_5: Dp,
    val grid_1: Dp,
    val grid_1_5: Dp,
    val grid_2: Dp,
    val grid_2_5: Dp,
    val grid_3: Dp,
    val grid_3_5: Dp,
    val grid_4: Dp,
    val grid_4_5: Dp,
    val grid_5: Dp,
    val grid_5_5: Dp,
    val grid_6: Dp,
    val grid_6_5: Dp,
    val grid_7: Dp,
    val grid_7_5: Dp,
    val grid_8: Dp,
    val plane_0: Dp,
    val plane_1: Dp,
    val plane_2: Dp,
    val plane_3: Dp,
    val plane_4: Dp,
    val plane_5: Dp,
    val minimum_touch_target: Dp = 48.dp,
    val text_small: TextUnit,
    val text_medium: TextUnit,
    val text_large: TextUnit,
)

val smallDimensions = Dimensions(
    grid_0_25 = 2.dp,
    grid_0_5 = 4.dp,
    grid_1 = 6.dp,
    grid_1_5 = 10.dp,
    grid_2 = 12.dp,
    grid_2_5 = 15.dp,
    grid_3 = 18.dp,
    grid_3_5 = 22.dp,
    grid_4 = 24.dp,
    grid_4_5 = 28.dp,
    grid_5 = 30.dp,
    grid_5_5 = 34.dp,
    grid_6 = 36.dp,
    grid_6_5 = 40.dp,
    grid_7 = 42.dp,
    grid_7_5 = 46.dp,
    grid_8 = 52.dp,
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_3 = 4.dp,
    plane_4 = 6.dp,
    plane_5 = 12.dp,
    text_small = 12.sp,
    text_medium = 14.sp,
    text_large = 16.sp,
)

val sw360Dimensions = Dimensions(
    grid_0_25 = 2.dp,
    grid_0_5 = 4.dp,
    grid_1 = 8.dp,
    grid_1_5 = 12.dp,
    grid_2 = 16.dp,
    grid_2_5 = 20.dp,
    grid_3 = 24.dp,
    grid_3_5 = 28.dp,
    grid_4 = 32.dp,
    grid_4_5 = 36.dp,
    grid_5 = 40.dp,
    grid_5_5 = 44.dp,
    grid_6 = 48.dp,
    grid_6_5 = 52.dp,
    grid_7 = 56.dp,
    grid_7_5 = 60.dp,
    grid_8 = 64.dp,
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_3 = 4.dp,
    plane_4 = 8.dp,
    plane_5 = 16.dp,
    text_small = 14.sp,
    text_medium = 16.sp,
    text_large = 18.sp,
)
