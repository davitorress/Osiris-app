package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.OsirisTheme
import com.papaya.osiris.ui.theme.White

@Composable
fun HomePage(
    navController: NavHostController,
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Scaffold(
        containerColor = White,
        bottomBar = { NavBar(navController) },
        contentWindowInsets = WindowInsets.navigationBars,
        modifier = Modifier.background(White)
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(PaddingValues(top = 22.dp, start = 22.dp, end = 22.dp))
            ) {
                SearchInput(
                    text = searchText,
                    placeholder = "Busque por plantas ou receitas",
                    onTextChange = { text -> searchText = text }
                )
                CardsSection(
                    title = "PANCs",
                    items = listOf(
                        Product("Inhame", "https://picsum.photos/400"),
                        Product("Vinagreira (Hibisco)", "https://picsum.photos/300"),
                        Product("Ora-pro-nóbis", "https://picsum.photos/700"),
                    )
                )
                CardsSection(
                    title = "Receitas",
                    items = listOf(
                        Product("Sopa de cevadinha", "https://picsum.photos/400"),
                        Product("Suco verde", "https://picsum.photos/300"),
                        Product("Salada amarga", "https://picsum.photos/700"),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun HomePagePreview() {
    OsirisTheme {
        HomePage(NavHostController(LocalContext.current))
    }
}