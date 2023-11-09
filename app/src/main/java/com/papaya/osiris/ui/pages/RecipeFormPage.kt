package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardReturn
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.*
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
import com.papaya.osiris.ui.theme.*

@Composable
fun RecipeFormPage(
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    pancs: List<String>,
    onPancsChange: (String, Int) -> Unit,
    onAddPanc: () -> Unit,
    onRemovePanc: () -> Unit,
    ingredients: List<String>,
    onIngredientsChange: (String, Int) -> Unit,
    onAddIngredients: () -> Unit,
    onRemoveIngredients: () -> Unit,
    prepair: List<String>,
    onPrepairChange: (String, Int) -> Unit,
    onAddPrepair: () -> Unit,
    onRemovePrepair: () -> Unit,
    successButtonText: String,
    cancelButtonText: String,
    onSuccess: () -> Unit,
    onCancel: () -> Unit,
    onSaveImage: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    imageURL: String? = null,
) {
    val pancsOptions = listOf("PANC 1", "PANC 2", "PANC 3", "PANC 4", "PANC 5")

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
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(PaddingValues(22.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Box(
                        contentAlignment = Alignment.TopStart,
                        propagateMinConstraints = true
                    ) {
                        ThemedButton(
                            onClick = onCancel,
                            theme = ButtonTheme.Medium,
                            circleShape = true,
                            icon = Icons.Filled.KeyboardReturn,
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp)
                            .align(Alignment.Center)
                            .background(Gray, CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            if (imageURL !== null) {
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
                                    imageVector = Icons.Filled.RestaurantMenu,
                                    contentDescription = null,
                                    tint = MediumGreen,
                                    modifier = Modifier
                                        .width(96.dp)
                                        .height(96.dp)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            ThemedButton(
                                onClick = onSaveImage,
                                theme = ButtonTheme.Medium,
                                circleShape = true,
                                icon = Icons.Filled.Edit,
                                modifier = Modifier.wrapContentSize()
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextInput(
                        label = "Nome:",
                        text = name,
                        onTextChange = onNameChange,
                        placeholder = "Digite o nome da receita"
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextAreaInput(
                            label = "Descrição curta:",
                            text = description,
                            onTextChange = onDescriptionChange,
                            placeholder = "Digite a descrição da receita"
                        )
                        Text(
                            text = "${description.length}/120 caracteres",
                            color = DarkGreen,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                    MultiInput(
                        label = "PANCs:",
                        actionAdd = onAddPanc,
                        actionRemove = onRemovePanc,
                    ) {
                        pancs.forEachIndexed { index, panc ->
                            SelectInput(
                                text = panc.ifEmpty { pancsOptions.first() },
                                options = pancsOptions,
                                onTextChange = { _, text -> onPancsChange(text, index) },
                            )
                        }
                    }
                    MultiInput(
                        label = "Ingredientes:",
                        actionAdd = onAddIngredients,
                        actionRemove = onRemoveIngredients,
                    ) {
                        ingredients.forEachIndexed { i, ingredient ->
                            TextInput(
                                text = ingredient,
                                placeholder = "Exemplo: 1 colher de sal",
                                onTextChange = { text -> onIngredientsChange(text, i) },
                            )
                        }
                    }
                    MultiInput(
                        label = "Modo de preparo:",
                        actionAdd = onAddPrepair,
                        actionRemove = onRemovePrepair,
                    ) {
                        prepair.forEachIndexed { i, step ->
                            TextAreaInput(
                                text = step,
                                label = "${i + 1}º Passo:",
                                onTextChange = { text -> onPrepairChange(text, i) },
                                placeholder = "Exemplo: Misture todos os ingredientes secos",
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(40.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ThemedButton(
                            onClick = onCancel,
                            theme = ButtonTheme.Wine,
                            text = cancelButtonText,
                            modifier = Modifier.weight(1f)
                        )
                        ThemedButton(
                            onClick = onSuccess,
                            theme = ButtonTheme.Medium,
                            text = successButtonText,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun RecipeFormPagePreview() {
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var pancs by rememberSaveable { mutableStateOf(listOf("")) }
    var ingredients by rememberSaveable { mutableStateOf(listOf("")) }
    var prepair by rememberSaveable { mutableStateOf(listOf("")) }

    OsirisTheme {
        RecipeFormPage(
            name = name,
            onNameChange = { name = it },
            description = description,
            onDescriptionChange = { description = it },
            pancs = pancs,
            onPancsChange = { panc, index ->
                pancs = pancs.toMutableList().apply { set(index, panc) }
            },
            onAddPanc = { pancs = pancs.toMutableList().apply { add("") } },
            onRemovePanc = { pancs = pancs.toMutableList().apply { removeLast() } },
            ingredients = ingredients,
            onIngredientsChange = { ingredient, index ->
                ingredients = ingredients.toMutableList().apply { set(index, ingredient) }
            },
            onAddIngredients = { ingredients = ingredients.toMutableList().apply { add("") } },
            onRemoveIngredients = { ingredients = ingredients.toMutableList().apply { removeLast() } },
            prepair = prepair,
            onPrepairChange = { step, index ->
                prepair = prepair.toMutableList().apply { set(index, step) }
            },
            onAddPrepair = { prepair = prepair.toMutableList().apply { add("") } },
            onRemovePrepair = { prepair = prepair.toMutableList().apply { removeLast() } },
            onSuccess = {  },
            onCancel = {  },
            onSaveImage = {  },
            successButtonText = "Salvar",
            cancelButtonText = "Cancelar",
            navController = NavHostController(LocalContext.current)
        )
    }
}
