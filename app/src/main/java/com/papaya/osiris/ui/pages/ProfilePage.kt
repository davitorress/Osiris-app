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
import androidx.compose.runtime.Composable
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
import com.papaya.osiris.data.Product
import com.papaya.osiris.data.User
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.*

@Composable
fun ProfilePage(
    user: User,
    pancs: List<Product>,
    recipes: List<Product>,
    myRecipes: List<Product>,
    onClickLogout: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
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
                    .padding(PaddingValues(22.dp))
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
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
                            if (!user.imagem.isNullOrEmpty()) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(user.imagem)
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
                        text = user.nome,
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
                                text = user.email,
                                color = Black,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        ThemedButton(
                            onClick = onClickLogout,
                            theme = ButtonTheme.Medium,
                            text = "Sair",
                            icon = Icons.Rounded.Logout,
                            modifier = Modifier.padding(4.dp).fillMaxWidth(0.45f),
                            textModifier = Modifier.padding(end = 2.dp)
                        )
                    }
                }
                if (pancs.isNotEmpty()) {
                    CardsSection(
                        title = "PANCs favoritas",
                        items = pancs
                    )
                }
                if (recipes.isNotEmpty()) {
                    CardsSection(
                        title = "Receitas favoritas",
                        items = recipes
                    )
                }
                if (myRecipes.isNotEmpty()) {
                    CardsSection(
                        title = "Minhas receitas",
                        items = myRecipes
                    )
                }
            }
        }
    }
}
