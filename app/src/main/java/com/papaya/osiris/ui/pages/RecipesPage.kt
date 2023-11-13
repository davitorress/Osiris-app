package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.services.RecipeWebClient
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.White

@Composable
fun RecipesPage(
    recipes: List<Recipe>,
    searchItems: List<Recipe>,
    searchText: String,
    onSearchChange: (String) -> Unit,
    onAddRecipe: () -> Unit,
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
                    placeholder = "Busque por receitas",
                    onTextChange = { text -> onSearchChange(text) }
                )
                ThemedButton(
                    onClick = onAddRecipe,
                    theme = ButtonTheme.Medium,
                    text = "Postar uma receita",
                    icon = Icons.Rounded.AddCircleOutline,
                    modifier = Modifier.fillMaxWidth(),
                    textModifier = Modifier.padding(end = 8.dp)
                )
                if (searchItems.isNotEmpty()) {
                    RecipesListSection(
                        title = "Receitas encontradas",
                        items = searchItems
                    )
                }
                RecipesListSection(
                    title = "Receitas",
                    items = recipes
                )
            }
        }
    }
}
