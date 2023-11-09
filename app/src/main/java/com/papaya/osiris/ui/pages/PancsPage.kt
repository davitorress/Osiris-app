package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun PancsPage(
    onClickPanc: (String) -> Unit,
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
                    .padding(PaddingValues(top = 22.dp, start = 22.dp, end = 22.dp))
            ) {
                SearchInput(
                    text = searchText,
                    placeholder = "Busque por pancs",
                    onTextChange = { text -> searchText = text }
                )
                PancsListSection(
                    title = "PANCs",
                    items = listOf(
                        Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                        Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                        Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                        Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
fun PancsPagePreview() {
    OsirisTheme {
        PancsPage(
            onClickPanc = {},
            navController = NavHostController(LocalContext.current)
        )
    }
}