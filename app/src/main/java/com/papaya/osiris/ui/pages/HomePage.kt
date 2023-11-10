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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.papaya.osiris.data.AuthViewModel
import com.papaya.osiris.data.Product
import com.papaya.osiris.services.PancWebClient
import com.papaya.osiris.services.RecipeWebClient
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.OsirisTheme
import com.papaya.osiris.ui.theme.White

@Composable
fun HomePage(
    navController: NavHostController,
    authViewModel: AuthViewModel,
) {
    val token by authViewModel.token.collectAsState()
    var searchText by rememberSaveable { mutableStateOf("") }
    var pancs by rememberSaveable { mutableStateOf(listOf<Product>()) }
    var recipes by rememberSaveable { mutableStateOf(listOf<Product>()) }

    PancWebClient().list({ pancList ->
        val productList = pancList.map {
            Product(it.id, it.nome, it.imagem) { navController.navigate("panc/${it.id}") }
        }
        pancs = productList
    })
    token?.let {
        RecipeWebClient().list(it, { recipeList ->
            val productList = recipeList.map { recipe ->
                Product(recipe.id, recipe.nome, recipe.imagem) { navController.navigate("recipe/${recipe.id}") }
            }
            recipes = productList
        })
    }

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
                    items = pancs
                )
                CardsSection(
                    title = "Receitas",
                    items = recipes
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 500)
@Composable
fun HomePagePreview() {
    OsirisTheme {
        HomePage(NavHostController(LocalContext.current), viewModel())
    }
}