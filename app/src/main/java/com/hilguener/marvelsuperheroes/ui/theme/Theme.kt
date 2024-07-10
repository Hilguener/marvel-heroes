package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.hilguener.marvelsuperheroes.ui.theme.Typography
import com.hilguener.marvelsuperheroes.ui.theme.backgroundDark
import com.hilguener.marvelsuperheroes.ui.theme.backgroundDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.backgroundDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.backgroundLight
import com.hilguener.marvelsuperheroes.ui.theme.backgroundLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.backgroundLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1ContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.customColor1ContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1ContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1ContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.customColor1ContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1ContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1Dark
import com.hilguener.marvelsuperheroes.ui.theme.customColor1DarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1DarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1Light
import com.hilguener.marvelsuperheroes.ui.theme.customColor1LightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.customColor1LightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.errorContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.errorContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorDark
import com.hilguener.marvelsuperheroes.ui.theme.errorDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorLight
import com.hilguener.marvelsuperheroes.ui.theme.errorLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.errorLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseOnSurfaceDark
import com.hilguener.marvelsuperheroes.ui.theme.inverseOnSurfaceDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseOnSurfaceLight
import com.hilguener.marvelsuperheroes.ui.theme.inverseOnSurfaceLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseOnSurfaceLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.inversePrimaryDark
import com.hilguener.marvelsuperheroes.ui.theme.inversePrimaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.inversePrimaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.inversePrimaryLight
import com.hilguener.marvelsuperheroes.ui.theme.inversePrimaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.inversePrimaryLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseSurfaceDark
import com.hilguener.marvelsuperheroes.ui.theme.inverseSurfaceDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseSurfaceDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseSurfaceLight
import com.hilguener.marvelsuperheroes.ui.theme.inverseSurfaceLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.inverseSurfaceLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onBackgroundDark
import com.hilguener.marvelsuperheroes.ui.theme.onBackgroundDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onBackgroundDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onBackgroundLight
import com.hilguener.marvelsuperheroes.ui.theme.onBackgroundLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onBackgroundLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1ContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1ContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1ContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1ContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1ContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1ContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1Dark
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1DarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1DarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1Light
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1LightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onCustomColor1LightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.onErrorContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.onErrorContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorDark
import com.hilguener.marvelsuperheroes.ui.theme.onErrorDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorLight
import com.hilguener.marvelsuperheroes.ui.theme.onErrorLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onErrorLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryDark
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryLight
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onPrimaryLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryDark
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryLight
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSecondaryLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceDark
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceLight
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceVariantDark
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceVariantDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceVariantDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceVariantLight
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceVariantLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onSurfaceVariantLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryDark
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryLight
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.onTertiaryLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineDark
import com.hilguener.marvelsuperheroes.ui.theme.outlineDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineLight
import com.hilguener.marvelsuperheroes.ui.theme.outlineLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineVariantDark
import com.hilguener.marvelsuperheroes.ui.theme.outlineVariantDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineVariantDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineVariantLight
import com.hilguener.marvelsuperheroes.ui.theme.outlineVariantLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.outlineVariantLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.primaryContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.primaryContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryDark
import com.hilguener.marvelsuperheroes.ui.theme.primaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryLight
import com.hilguener.marvelsuperheroes.ui.theme.primaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.primaryLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.scrimDark
import com.hilguener.marvelsuperheroes.ui.theme.scrimDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.scrimDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.scrimLight
import com.hilguener.marvelsuperheroes.ui.theme.scrimLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.scrimLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.secondaryContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.secondaryContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryDark
import com.hilguener.marvelsuperheroes.ui.theme.secondaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryLight
import com.hilguener.marvelsuperheroes.ui.theme.secondaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.secondaryLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceBrightDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceBrightDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceBrightDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceBrightLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceBrightLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceBrightLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighestDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighestDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighestLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighestLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerHighestLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowestDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowestDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowestLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowestLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceContainerLowestLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDimDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDimDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDimDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDimLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDimLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceDimLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceVariantDark
import com.hilguener.marvelsuperheroes.ui.theme.surfaceVariantDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceVariantDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceVariantLight
import com.hilguener.marvelsuperheroes.ui.theme.surfaceVariantLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.surfaceVariantLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryContainerDark
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryContainerDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryContainerDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryContainerLight
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryContainerLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryContainerLightMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryDark
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryDarkHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryDarkMediumContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryLight
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryLightHighContrast
import com.hilguener.marvelsuperheroes.ui.theme.tertiaryLightMediumContrast

@Immutable
data class ExtendedColorScheme(
    val customColor1: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1Light,
  onCustomColor1Light,
  customColor1ContainerLight,
  onCustomColor1ContainerLight,
  ),
)

val extendedDark = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1Dark,
  onCustomColor1Dark,
  customColor1ContainerDark,
  onCustomColor1ContainerDark,
  ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1LightMediumContrast,
  onCustomColor1LightMediumContrast,
  customColor1ContainerLightMediumContrast,
  onCustomColor1ContainerLightMediumContrast,
  ),
)

val extendedLightHighContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1LightHighContrast,
  onCustomColor1LightHighContrast,
  customColor1ContainerLightHighContrast,
  onCustomColor1ContainerLightHighContrast,
  ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1DarkMediumContrast,
  onCustomColor1DarkMediumContrast,
  customColor1ContainerDarkMediumContrast,
  onCustomColor1ContainerDarkMediumContrast,
  ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1DarkHighContrast,
  onCustomColor1DarkHighContrast,
  customColor1ContainerDarkHighContrast,
  onCustomColor1ContainerDarkHighContrast,
  ),
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun MarvelSuperHeroesTheme (
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

