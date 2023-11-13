package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.papaya.osiris.data.Panc
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.Black
import com.papaya.osiris.ui.theme.Gray
import com.papaya.osiris.ui.theme.White

@Composable
fun PancPage(
    panc: Panc,
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
                                .data(panc.imagem)
                                .crossfade(true)
                                .build(),
                            contentDescription = panc.nome,
                            modifier = Modifier
                                .width(128.dp)
                                .height(132.dp)
                                .clip(MaterialTheme.shapes.small),
                            placeholder = ColorPainter(Gray),
                            contentScale = ContentScale.Crop
                        )
                        ThemedTextButton(
                            text = if (isFavorite) "Favorito" else "Favoritar",
                            theme = ButtonTheme.Medium,
                            onClick = { onFavoriteClick(!isFavorite) },
                            icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
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
                            text = panc.nome,
                            color = Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = panc.descricao,
                            color = Black,
                            style = MaterialTheme.typography.bodySmall
                        )
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
                        text = "Benefícios",
                        color = Black,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = panc.beneficios,
                        color = Black,
                        style = MaterialTheme.typography.bodyMedium
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
                        text = "Modo de Cultivo",
                        color = Black,
                        style = MaterialTheme.typography.titleSmall
                    )

                    panc.cultivo.forEach { item ->
                        Text(
                            text = item,
                            color = Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
