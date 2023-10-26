package com.papaya.osiris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.papaya.osiris.ui.components.CardsCarousel
import com.papaya.osiris.ui.components.Product
import com.papaya.osiris.ui.pages.LoginPage
import com.papaya.osiris.ui.pages.RegisterPage
import com.papaya.osiris.ui.theme.OsirisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OsirisTheme {
                CardsCarousel(
                    listOf(
                        Product("Item 1", "https://picsum.photos/128/196"),
                        Product("Item 2", "https://picsum.photos/128/196"),
                        Product("Item 3", "https://picsum.photos/128/196"),
                        Product("Item 4", "https://picsum.photos/128/196"),
                    )
                )
            }
        }
    }
}
