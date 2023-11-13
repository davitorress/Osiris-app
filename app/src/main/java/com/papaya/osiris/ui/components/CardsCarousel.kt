package com.papaya.osiris.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.papaya.osiris.data.Product
import com.papaya.osiris.ui.theme.Black
import com.papaya.osiris.ui.theme.DarkGreen
import com.papaya.osiris.ui.theme.Gray
import com.papaya.osiris.ui.theme.OsirisTheme

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
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .width(128.dp)
                .height(128.dp)
                .clip(MaterialTheme.shapes.small),
            placeholder = ColorPainter(Gray),
            contentScale = ContentScale.Crop
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
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
        modifier = modifier
    ) {
        items(items) { item ->
            CardItem(
                title = item.name,
                imageURL = item.image,
                modifier = Modifier.clickable { item.clickAction.onClick() }
            )
        }
    }
}

@Composable
fun CardsSection(
    title: String,
    items: List<Product>,
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
        CardsCarousel(items)
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
//        CardsCarousel(
//            listOf(
//                Product("Item 1", "https://picsum.photos/400"),
//                Product("Item 2", "https://picsum.photos/300"),
//                Product("Item 3", "https://picsum.photos/700"),
//                Product("Item 4", "https://picsum.photos/500"),
//            )
//        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun CardsSectionPreview() {
    OsirisTheme {
//        CardsSection(
//            title = "Seção",
//            items = listOf(
//                Product("Item 1", "https://picsum.photos/400"),
//                Product("Item 2", "https://picsum.photos/300"),
//                Product("Item 3", "https://picsum.photos/700"),
//                Product("Item 4", "https://picsum.photos/500"),
//            )
//        )
    }
}