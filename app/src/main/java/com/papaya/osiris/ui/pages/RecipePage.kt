package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papaya.osiris.data.Recipe
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.*

@Composable
fun RecipePage(
    recipe: Recipe,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        containerColor = White,
        bottomBar = { NavBar(navController) },
        contentWindowInsets = WindowInsets.navigationBars,
        modifier = modifier.background(White)
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
                    .padding(PaddingValues(top = 44.dp, start = 22.dp, end = 22.dp, bottom = 22.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .wrapContentSize()
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(recipe.imagem)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = recipe.nome,
                                modifier = Modifier
                                    .width(128.dp)
                                    .height(132.dp)
                                    .clip(MaterialTheme.shapes.small),
                                placeholder = ColorPainter(Gray),
                                contentScale = ContentScale.Crop
                            )
                            ThemedTextButton(
                                text = if (isFavorite) "Salvo" else "Salvar",
                                theme = ButtonTheme.Medium,
                                onClick = { onFavoriteClick(!isFavorite) },
                                icon = if (isFavorite) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = recipe.nome,
                                color = Black,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = recipe.descricao,
                                color = Black,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(2.dp)
                    ) {
                        items(items = recipe.pancs) { panc ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Eco,
                                    contentDescription = null,
                                    tint = MediumGreen,
                                    modifier = Modifier
                                        .width(18.dp)
                                )
                                Text(
                                    text = panc,
                                    color = MediumGreen,
                                    fontWeight = FontWeight.SemiBold,
                                    style = MaterialTheme.typography.bodySmall,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "Ingredientes",
                        color = Black,
                        style = MaterialTheme.typography.titleSmall
                    )
                    BulletList(recipe.ingredientes)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "Modo de preparo",
                        color = Black,
                        style = MaterialTheme.typography.titleSmall
                    )
                    NumericList(recipe.preparo)
                }
            }
        }
    }
}