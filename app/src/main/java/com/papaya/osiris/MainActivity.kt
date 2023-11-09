package com.papaya.osiris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.papaya.osiris.navigation.OsirisNavHost
import com.papaya.osiris.ui.theme.OsirisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OsirisTheme {
                val navController: NavHostController = rememberNavController()
                OsirisNavHost(navController)
            }
        }
    }
}
