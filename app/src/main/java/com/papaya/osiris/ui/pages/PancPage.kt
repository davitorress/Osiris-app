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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.Black
import com.papaya.osiris.ui.theme.Gray
import com.papaya.osiris.ui.theme.OsirisTheme
import com.papaya.osiris.ui.theme.White

@Composable
fun PancPage(
    title: String,
    imageURL: String,
    description: String,
    benefits: String,
    farming: List<String>,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    pancId: String? = null,
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
                    .padding(PaddingValues(top = 44.dp, start = 22.dp, end = 22.dp))
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
                        text = benefits,
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

                    farming.forEach { item ->
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

@Preview(showBackground = true, widthDp = 375)
@Composable
fun PancPagePreview() {
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    OsirisTheme {
        PancPage(
            title = "Ora-pro-nóbis",
            imageURL = "https://picsum.photos/126/132",
            description = "A ora-pro-nóbis é um tipo de planta trepadeira altamente nutritiva: tem alto teor de proteínas, fibras, vitaminas e minerais importantes.",
            benefits = "A ora-pro-nóbis, também conhecida como Pereskia aculeata, é uma planta valorizada por sua riqueza nutricional e versatilidade culinária. Rica em nutrientes essenciais, fibras e antioxidantes, ela oferece benefícios como melhoria da digestão, fortalecimento do sistema imunológico, redução da inflamação e contribuição para a saúde óssea. Sua inclusão na dieta pode promover uma alimentação equilibrada e saudável.",
            farming = listOf(
                "Para cultivá-lo, é importante escolher um local que receba bastante luz solar, mas também que tenha sombra parcial durante o dia.",
                "As sementes podem ser plantadas diretamente no solo ou em recipientes, e devem ser mantidas úmidas até que as mudas surjam. O espaçamento entre as plantas deve ser de cerca de 15 a 20 cm para permitir o desenvolvimento adequado das folhas.",
                "Durante o cultivo, é importante manter o solo úmido, mas não encharcado, para evitar o apodrecimento das raízes. O espinafre deve ser colhido quando as folhas atingirem o tamanho desejado, geralmente cerca de 6 a 8 semanas após a semeadura.",
            ),
            isFavorite = isFavorite,
            onFavoriteClick = { isFavorite = it },
            navController = NavHostController(LocalContext.current)
        )
    }
}