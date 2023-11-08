package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.*

@Composable
fun ProfilePage(
    name: String,
    email: String,
    pancs: List<Product>,
    recipes: List<Product>,
    myRecipes: List<Product>,
    modifier: Modifier = Modifier,
    imageURL: String? = null,
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(3) }

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
                            if (!imageURL.isNullOrEmpty()) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(imageURL)
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
                        text = name,
                        color = Black,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = null,
                            tint = MediumGreen,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                        Text(
                            text = email,
                            color = Black,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                CardsSection(
                    title = "PANCs favoritas",
                    items = pancs
                )
                CardsSection(
                    title = "Receitas favoritas",
                    items = recipes
                )
                CardsSection(
                    title = "Minhas receitas",
                    items = myRecipes
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun ProfilePagePreview() {
    OsirisTheme {
        ProfilePage(
            name = "João da Silva",
            email = "joao@gmail.com",
            pancs = listOf(
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
            ),
            recipes = listOf(
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
            ),
            myRecipes = listOf(
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
                Product("Inhame", "https://picsum.photos/86"),
            ),
            imageURL = "https://picsum.photos/128",
        )
    }
}
