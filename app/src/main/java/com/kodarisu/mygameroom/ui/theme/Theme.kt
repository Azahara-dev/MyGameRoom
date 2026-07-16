package com.kodarisu.mygameroom.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val GameRoomColorScheme = darkColorScheme(
    primary = AccentPrimary,
    onPrimary = TextPrimary,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextPrimary
)

@Composable
fun MyGameRoomTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GameRoomColorScheme,
        typography = GameRoomTypography,
        content = content
    )
}