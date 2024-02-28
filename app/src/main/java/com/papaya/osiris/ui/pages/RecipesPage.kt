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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.navigation.RecipeFormDestination
import com.papaya.osiris.navigation.navigateComplete
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.White
import com.papaya.osiris.viewmodel.AuthViewModel
import com.papaya.osiris.viewmodel.RecipeViewModel
import com.papaya.osiris.viewmodel.UserViewModel

@Composable
fun RecipesPage(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
) {
    val userId = authViewModel.userId
    val token by authViewModel.token.observeAsState()
    val user by userViewModel.user.observeAsState(null)
    val recipes by recipeViewModel.recipes.observeAsState(null)

    var search by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        userId?.let { userViewModel.fetch(token!!, userId, {}) }
        recipeViewModel.fetch({})
    }

    // TODO: Implement conversion of recipes to have isFavorite property

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
                    placeholder = "Busque por receitas",
                    onTextChange = { text -> search = text }
                )

                ThemedButton(
                    onClick = { navController.navigateComplete("${RecipeFormDestination.route}/new")},
                    theme = ButtonTheme.Medium,
                    text = "Postar uma receita",
                    icon = Icons.Rounded.AddCircleOutline,
                    modifier = Modifier.fillMaxWidth(),
                    textModifier = Modifier.padding(end = 8.dp)
                )

                // TODO: Implement search
//                if (search.isNotBlank()) {
//                    val searchItems = recipes?.filter { recipe -> recipe.nome.contains(search, ignoreCase = true) }
//
//                    searchItems?.let {
//                        RecipesListSection(
//                            title = "Receitas encontradas",
//                            items = searchItems
//                        )
//                    }
//                }

                // TODO: Implement recipes list section
//                RecipesListSection(
//                    title = "Receitas",
//                    items = recipes
//                )
            }
        }
    }
}
