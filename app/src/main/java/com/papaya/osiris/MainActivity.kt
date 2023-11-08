package com.papaya.osiris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.papaya.osiris.ui.pages.*
import com.papaya.osiris.ui.theme.OsirisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                )
            }
        }
    }
}
