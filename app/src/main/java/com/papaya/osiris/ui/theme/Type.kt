package com.papaya.osiris.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.papaya.osiris.R

val GoogleFrontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val NunitoFont = GoogleFont("Nunito")
val UbuntuFont = GoogleFont("Ubuntu")

val NunitoFamily = FontFamily(
    Font(googleFont = NunitoFont, fontProvider = GoogleFrontProvider, style = FontStyle.Normal)
)
val UbuntuFamily = FontFamily(
    Font(googleFont = UbuntuFont, fontProvider = GoogleFrontProvider, style = FontStyle.Normal)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),

    displayLarge = TextStyle(
        fontFamily = UbuntuFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = UbuntuFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
)