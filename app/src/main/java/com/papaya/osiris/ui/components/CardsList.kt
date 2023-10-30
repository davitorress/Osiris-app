package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papaya.osiris.ui.theme.*

@Composable
fun CardListItem(
    title: String,
    imageURL: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    ingredients: List<String>? = null,
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .width(86.dp)
                .height(86.dp)
                .clip(MaterialTheme.shapes.small),
            placeholder = ColorPainter(Gray),
            contentScale = ContentScale.Fit
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = DarkGreen,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MediumGreen
                )
            }
            Text(
                text = description,
                color = Black,
                style = MaterialTheme.typography.bodySmall
            )
            if (!ingredients.isNullOrEmpty()) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    items(items = ingredients) { ingredient ->
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
                                text = ingredient,
                                color = MediumGreen,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

class Panc(var nome: String, var description: String, var isFavorite: Boolean, var image: String)

@Composable
fun PancsList(
    items: List<Panc>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxWidth().wrapContentHeight()
    ) {
        items(items = items) {panc ->
            CardListItem(
                title = panc.nome,
                imageURL = panc.image,
                description = panc.description,
                icon = if (panc.isFavorite) Icons.Filled.Favorite
                    else Icons.Filled.FavoriteBorder
            )
        }
    }
}

@Composable
fun PancsListSection(
    title: String,
    items: List<Panc>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            color = Black,
            style = MaterialTheme.typography.titleSmall
        )
        PancsList(items)
    }
}

class Recipe(var nome: String, var description: String, var isFavorite: Boolean, var image: String, var ingredients: List<String>)

@Composable
fun RecipesList(
    items: List<Recipe>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxWidth().wrapContentHeight()
    ) {
        items(items = items) {recipe ->
            CardListItem(
                title = recipe.nome,
                imageURL = recipe.image,
                description = recipe.description,
                ingredients = recipe.ingredients,
                icon = if (recipe.isFavorite) Icons.Filled.Bookmark
                    else Icons.Filled.BookmarkBorder,
            )
        }
    }
}

@Composable
fun RecipesListSection(
    title: String,
    items: List<Recipe>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            color = Black,
            style = MaterialTheme.typography.titleSmall
        )
        RecipesList(items)
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun CardListItemPreview() {
    OsirisTheme {
        CardListItem(
            title = "Receita",
            imageURL = "https://picsum.photos/86",
            description = "A salada amarga é um acompanhamento simples com o foco de obter uma variedade de nutrientes.",
            icon = Icons.Filled.Bookmark,
            ingredients = listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun PancsListPreview() {
    OsirisTheme {
        PancsList(
            listOf(
                Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
                Panc("Inhame", "descrição", false, "https://picsum.photos/86"),
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun RecipesListPreview() {
    OsirisTheme {
        RecipesList(
            listOf(
                Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
                Recipe("Inhame", "descrição", false, "https://picsum.photos/86", listOf("Inhame", "Ora-pro-nóbis", "Hortelâ")),
            )
        )
    }
}