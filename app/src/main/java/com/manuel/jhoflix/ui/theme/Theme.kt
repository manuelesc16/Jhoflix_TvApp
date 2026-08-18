package com.manuel.jhoflix.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Paleta oscura, elegante, con acento rojo/vino como identidad de marca
val JhoflixBackground = Color(0xFF0B0B0F)
val JhoflixSurface = Color(0xFF17171D)
val JhoflixAccent = Color(0xFFE50914)
val JhoflixAccentSoft = Color(0xFFB0060F)
val JhoflixTextPrimary = Color(0xFFF5F5F5)
val JhoflixTextSecondary = Color(0xFFA0A0A8)

private val JhoflixColorScheme = darkColorScheme(
    primary = JhoflixAccent,
    onPrimary = Color.White,
    secondary = JhoflixAccentSoft,
    background = JhoflixBackground,
    surface = JhoflixSurface,
    onSurface = JhoflixTextPrimary,
    onBackground = JhoflixTextPrimary,
)

@Composable
fun JhoflixTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = JhoflixColorScheme,
        content = content
    )
}
