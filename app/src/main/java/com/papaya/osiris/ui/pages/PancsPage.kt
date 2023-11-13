package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.White

@Composable
fun PancsPage(
    pancs: List<Panc>,
    searchItems: List<Panc>,
    searchText: String,
    onSearchChange: (String) -> Unit,
    navController: NavHostController,
) {
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
                    .padding(PaddingValues(22.dp))
            ) {
                SearchInput(
                    text = searchText,
                    placeholder = "Busque por pancs",
                    onTextChange = { text -> onSearchChange(text) }
                )
                if (searchItems.isNotEmpty()) {
                    PancsListSection(
                        title = "PANCs encontradas",
                        items = searchItems
                    )
                }
                PancsListSection(
                    title = "PANCs",
                    items = pancs
                )
            }
        }
    }
}