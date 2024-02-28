package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papaya.osiris.navigation.*
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.*
import com.papaya.osiris.utils.convertToProduct
import com.papaya.osiris.viewmodel.AuthViewModel
import com.papaya.osiris.viewmodel.PancViewModel
import com.papaya.osiris.viewmodel.RecipeViewModel
import com.papaya.osiris.viewmodel.UserViewModel

@Composable
fun ProfilePage(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    pancViewModel: PancViewModel,
    recipeViewModel: RecipeViewModel,
) {
    val userId = authViewModel.userId
    val token by authViewModel.token.observeAsState()
    val user by userViewModel.user.observeAsState(null)
    val pancs by pancViewModel.pancs.observeAsState(null)
    val recipes by recipeViewModel.recipes.observeAsState(null)

    LaunchedEffect(Unit) {
        if(userId.isNullOrBlank() || token.isNullOrBlank()) {
            // TODO: Implement logout flow
            navController.navigateComplete(LoginDestination.route)
        }
        userId?.let { userViewModel.fetch(token!!, userId, {}) }
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
                    .padding(PaddingValues(22.dp))
                    .verticalScroll(rememberScrollState())
            ) {
                user?.let { u ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(128.dp)
                                .height(128.dp)
                                .background(Gray, CircleShape)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                            ) {
                                if (u.imagem.isNotBlank()) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(u.imagem)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(128.dp)
                                            .height(128.dp)
                                            .clip(CircleShape),
                                        placeholder = ColorPainter(Gray),
                                        contentScale = ContentScale.Fit
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.PersonOutline,
                                        contentDescription = null,
                                        tint = MediumGreen,
                                        modifier = Modifier
                                            .width(96.dp)
                                            .height(96.dp)
                                    )
                                }
                            }
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = u.nome,
                            color = Black,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.MailOutline,
                                    contentDescription = null,
                                    tint = MediumGreen,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                )
                                Text(
                                    text = u.email,
                                    color = Black,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                            ThemedButton(
                                onClick = {
                                    // TODO: Implement logout flow
                                    navController.navigateComplete(LoginDestination.route)
                                },
                                theme = ButtonTheme.Medium,
                                text = "Sair",
                                icon = Icons.Rounded.Logout,
                                modifier = Modifier.padding(4.dp).fillMaxWidth(0.45f),
                                textModifier = Modifier.padding(end = 2.dp)
                            )
                        }
                    }

                    pancs?.let { pancs ->
                        val favorites = pancs.filter { p -> u.pancsFavoritasId.contains(p.id) }

                        CardsSection(
                            title = "PANCs favoritas",
                            items = favorites.map { p ->
                                convertToProduct(
                                    panc = p,
                                    clickAction = {
                                        navController.navigateComplete("${PancDestination.route}/${p.id}")
                                    }
                                )
                            }
                        )
                    }

                    recipes?.let { recipes ->
                        val favorites = recipes.filter { r -> u.receitasSalvasId.contains(r.id) }
                        val myRecipes = recipes.filter { r -> r.usuarioId == u.id }

                        CardsSection(
                            title = "Receitas favoritas",
                            items = favorites.map { r ->
                                convertToProduct(
                                    recipe = r,
                                    clickAction = {
                                        navController.navigateComplete("${RecipeDestination.route}/${r.id}")
                                    }
                                )
                            }
                        )

                        CardsSection(
                            title = "Minhas receitas",
                            items = myRecipes.map { r ->
                                convertToProduct(
                                    recipe = r,
                                    clickAction = {
                                        navController.navigateComplete("${RecipeFormDestination.route}/${r.id}")
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
