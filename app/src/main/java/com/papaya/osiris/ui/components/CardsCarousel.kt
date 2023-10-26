package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.papaya.osiris.ui.theme.DarkGreen
import com.papaya.osiris.ui.theme.OsirisTheme

class Product(var name: String, var image: String) {}

@Composable
fun CardItem(
    title: String,
    imageURL: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.wrapContentHeight().width(128.dp)
    ) {
        AsyncImage(
            model = imageURL,
            contentDescription = title,
            modifier = Modifier
                .width(128.dp)
                .height(128.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = title,
            color = DarkGreen,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CardsCarousel(
    items: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
        modifier = modifier
    ) {
        items(items) { item ->
            CardItem(title = item.name, imageURL = item.image)
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun CardItemPreview() {
    OsirisTheme {
        CardItem(
            title = "Imagem",
            imageURL = "https://picsum.photos/128/196"
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun CardsCarouselPreview() {
    OsirisTheme {
        CardsCarousel(
            listOf(
                Product("Item 1", "https://picsum.photos/128/196"),
                Product("Item 2", "https://picsum.photos/128/196"),
                Product("Item 3", "https://picsum.photos/128/196"),
                Product("Item 4", "https://picsum.photos/128/196"),
            )
        )
    }
}