package com.papaya.osiris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.papaya.osiris.ui.pages.HomePage
import com.papaya.osiris.ui.pages.LoginPage
import com.papaya.osiris.ui.pages.RecipesPage
import com.papaya.osiris.ui.pages.RegisterPage
import com.papaya.osiris.ui.theme.OsirisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OsirisTheme {
                RecipesPage()
            }
        }
    }
}
