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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.*

@Composable
fun RecipePage(
    title: String,
    imageURL: String,
    description: String,
    pancs: List<String>,
    ingredients: List<String>,
    prepair: List<String>,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        containerColor = White,
        bottomBar = { NavBar(userLinks) },
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
                    .padding(PaddingValues(top = 44.dp, start = 22.dp, end = 22.dp))
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
                                    .data(imageURL)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = title,
                                modifier = Modifier
                                    .width(128.dp)
                                    .height(132.dp)
                                    .clip(MaterialTheme.shapes.small),
                                placeholder = ColorPainter(Gray),
                                contentScale = ContentScale.Fit
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
                                text = title,
                                color = Black,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = description,
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
                        items(items = pancs) { panc ->
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
                    BulletList(ingredients)
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
                    NumericList(prepair)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun RecipePagePreview() {
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    OsirisTheme {
        RecipePage(
            title = "Sopa de cevadinha",
            imageURL = "https://picsum.photos/128/132",
            description = "Nessa sopa, a ora-pro-nóbis é combinada com cevadinha, linguiça, bacon, cenoura, batata, tomate e alho-poró, além de ervas e temperos.",
            pancs = listOf("Ora-pro-nóbis", "Espinafre"),
            ingredients = listOf(
                "Linguiça picada a gosto",
                "Bacon picado a gosto",
                "Cebola desidratada a gosto",
                "Alho-poró desidratado a gosto",
                "Chimichuri a gosto",
                "Gengibre a gosto",
                "Cúrcuma a gosto",
                "Mostarda em pó a gosto",
                "1 folha de louro",
                "3 cenouras pequenas picadinhas",
            ),
            prepair = listOf(
                "Em uma panela, frite a linguiça na própria gordura até dourar e reserve.",
                "Na mesma panela, frite o bacon até dourar e reserve.",
                "Retorne a panela ao fogo e refogue a cebola desidratada com alho-poró desidratado, chimichurri, gengibre, cúrcuma, mostarda em pó e louro.",
                "Adicione a cenoura, batata, tomate e alho-poró e refogue rapidamente.",
                "Reserve tudo em um recipiente separado e retorne a panela ao fogo.",
                "Coloque a cevadinha e a água e cozinhe por cerca de 30 minutos.",
                "Quando a cevadinha estiver bem cozida, retorne à panela os legumes, o bacon e a linguiça.",
                "Acrescente o ora-pro-nóbis e cozinhe por cerca de 10 minutos.",
                "Tempere com sal e finalize com salsinha.",
                "Agora é só servir! Bom apetite."
            ),
            isFavorite = isFavorite,
            onFavoriteClick = { isFavorite = it }
        )
    }
}