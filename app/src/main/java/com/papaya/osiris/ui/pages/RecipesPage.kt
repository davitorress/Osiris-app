package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.OsirisTheme
import com.papaya.osiris.ui.theme.White

@Composable
fun RecipesPage() {
    var searchText by rememberSaveable { mutableStateOf("") }
    var selectedItem by rememberSaveable { mutableIntStateOf(2) }

    Scaffold(
        containerColor = White,
        bottomBar = { NavBar(userLinks, selectedItem, { selectedItem = it }) },
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
                    placeholder = "Busque por receitas",
                    onTextChange = { text -> searchText = text }
                )
                ThemedButton(
                    onClick = {  },
                    theme = ButtonTheme.Medium,
                    text = "Postar uma receita",
                    icon = Icons.Rounded.AddCircleOutline,
                    textModifier = Modifier.padding(end = 8.dp)
                )
                RecipesListSection(
                    title = "Receitas",
                    items = listOf(
                        Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                        Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                        Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                        Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
fun RecipesPagePreview() {
    OsirisTheme {
        RecipesPage()
    }
}