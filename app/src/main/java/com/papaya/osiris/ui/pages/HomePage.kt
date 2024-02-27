package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.navigation.PancDestination
import com.papaya.osiris.navigation.RecipeDestination
import com.papaya.osiris.navigation.navigateComplete
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.White
import com.papaya.osiris.utils.convertToProduct
import com.papaya.osiris.viewmodel.PancViewModel
import com.papaya.osiris.viewmodel.RecipeViewModel

@Composable
fun HomePage(
    navController: NavHostController,
    pancViewModel: PancViewModel,
    recipeViewModel: RecipeViewModel,
) {
    val pancs by pancViewModel.pancs.observeAsState(null)
    val recipes by recipeViewModel.recipes.observeAsState(null)

    var search by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        pancViewModel.fetch({})
        recipeViewModel.fetch({})
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
                    .padding(PaddingValues(22.dp))
            ) {
                SearchInput(
                    text = search,
                    placeholder = "Busque por plantas ou receitas",
                    onTextChange = { text -> search = text },
                )

                // TODO: implement search items section for recipes and pancs
//                if (search.isNotEmpty()) {
//                   PancsListSection(
//                        title = "PANCs encontradas",
//                        items = searchItems
//                    )
//                }

                pancs?.let { pancList ->
                    val items = pancList.map { panc ->
                        convertToProduct(panc = panc) {
                            navController.navigateComplete("${PancDestination.route}/${panc.id}")
                        }
                    }

                    CardsSection(
                        title = "PANCs",
                        items = items
                    )
                }

                recipes?.let { recipeList ->
                    val items = recipeList.map { recipe ->
                        convertToProduct(recipe = recipe) {
                            navController.navigateComplete("${RecipeDestination.route}/${recipe.id}")
                        }
                    }

                    CardsSection(
                        title = "Receitas",
                        items = items
                    )
                }
            }
        }
    }
}