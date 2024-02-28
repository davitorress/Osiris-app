package com.papaya.osiris.navigation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.papaya.osiris.data.*
import com.papaya.osiris.services.*
import com.papaya.osiris.ui.pages.*
import com.papaya.osiris.viewmodel.AuthViewModel
import com.papaya.osiris.viewmodel.PancViewModel
import com.papaya.osiris.viewmodel.RecipeViewModel
import com.papaya.osiris.viewmodel.UserViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@Composable
fun OsirisNavHost(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {
    val authViewModel: AuthViewModel = viewModel()
    val pancViewModel: PancViewModel = viewModel()
    val recipeViewModel: RecipeViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = LoginDestination.route,
        modifier = modifier
    ) {
        composable(route = LoginDestination.route) {
            LoginPage(
                navController = navController,
                viewModel = authViewModel,
            )
        }
        composable(route = RegisterDestination.route) {
            RegisterPage(
                navController = navController,
                viewModel = authViewModel,
            )
        }
        composable(route = HomeDestination.route) {
            HomePage(
                navController = navController,
                pancViewModel = pancViewModel,
                recipeViewModel = recipeViewModel,
            )
        }
        composable(route = PancsDestination.route) {
            PancsPage(
                navController = navController,
                pancViewModel = pancViewModel,
                authViewModel = authViewModel,
                userViewModel = userViewModel,
            )
        }
        composable(
            route = PancDestination.route,
            arguments = PancDestination.arguments,
            deepLinks = PancDestination.deepLinks,
        ) {
            val pancId = it.arguments?.getString(PancDestination.pancIdArg)

            if (pancId.isNullOrBlank()) navController.navigateComplete(PancsDestination.route)
            else {
                PancPage(
                    navController = navController,
                    pancViewModel = pancViewModel,
                    authViewModel = authViewModel,
                    userViewModel = userViewModel,
                    pancId = pancId,
                )
            }
        }
        composable(route = RecipesDestination.route) {
            RecipesPage(
                navController = navController,
                recipeViewModel = recipeViewModel,
                authViewModel = authViewModel,
                userViewModel = userViewModel,
            )
        }
        composable(
            route = RecipeDestination.route,
            arguments = RecipeDestination.arguments,
            deepLinks = RecipeDestination.deepLinks,
        ) {
            val recipeId = it.arguments?.getString(RecipeDestination.recipeIdArg)

            if (recipeId.isNullOrBlank()) navController.navigateComplete(RecipesDestination.route)
            else {
                RecipePage(
                    navController = navController,
                    recipeViewModel = recipeViewModel,
                    authViewModel = authViewModel,
                    userViewModel = userViewModel,
                    recipeId = recipeId,
                )
            }
        }
        composable(route = ProfileDestination.route) {
            ProfilePage(
                navController = navController,
                authViewModel = authViewModel,
                userViewModel = userViewModel,
                pancViewModel = pancViewModel,
                recipeViewModel = recipeViewModel,
            )
        }
        composable(
            route = RecipeFormDestination.route,
            arguments = RecipeFormDestination.arguments,
            deepLinks = RecipeFormDestination.deepLinks,
        ) { backStackEntry ->
            val token by authViewModel.token.collectAsState()
            var recipe by rememberSaveable { mutableStateOf<Recipe?>(null) }
            val formId = backStackEntry.arguments?.getString(RecipeFormDestination.formIdArg)

            if (formId == "new") {
                var name by rememberSaveable { mutableStateOf("") }
                var description by rememberSaveable { mutableStateOf("") }
                var pancs by rememberSaveable { mutableStateOf(listOf("")) }
                var ingredients by rememberSaveable { mutableStateOf(listOf("")) }
                var prepair by rememberSaveable { mutableStateOf(listOf("")) }
                var image by rememberSaveable { mutableStateOf<Uri?>(null) }

                val getContent = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { result: Uri? ->
                    result?.let { uri ->
                        image = uri
                    }
                }

                val onSuccess = {
                    RecipeWebClient().create(
                        token = token!!,
                        RecipeRequest(
                            name,
                            description,
                            pancs,
                            ingredients,
                            prepair,
                            imagem = image?.toString(),
                            authViewModel.userId
                        ), { created ->
                            val fileImage = convertContentUriToFile(context, image!!)
                            RecipeWebClient().updateImage(
                                token = token!!,
                                id = created.id,
                                imagem = MultipartBody.Part.createFormData(
                                    "imagem",
                                    fileImage!!.name,
                                    fileImage.asRequestBody()
                                ), {
                                    navController.navigateComplete("${RecipeDestination.route}/${created.id}")
                                }, {
                                    Log.e("RecipeWebClient.updateImage()", "failure")
                                    navController.navigateComplete("${RecipeDestination.route}/${created.id}")
                                }
                            )
                        }, {
                            Log.e("RecipeWebClient.create()", "failure")
                            navController.navigateComplete(RecipesDestination.route)
                        }
                    )
                }

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
                    onSuccess = onSuccess,
                    onCancel = { navController.navigateComplete(RecipesDestination.route) },
                    onEditImage = { getContent.launch(arrayOf("image/*")) },
                    successButtonText = "Salvar",
                    cancelButtonText = "Cancelar",
                    navController = navController,
                    imageURL = image?.toString(),
                )
            } else {
                RecipeWebClient().get(formId!!, { res ->
                    recipe = res
                }, {
                    Log.e("RecipeWebClient.get()", "failure")
                    navController.navigateComplete(ProfileDestination.route)
                })

                recipe?.let { recipeData ->
                    var name by rememberSaveable { mutableStateOf(recipeData.nome) }
                    var description by rememberSaveable { mutableStateOf(recipeData.descricao) }
                    var pancs by rememberSaveable { mutableStateOf(recipeData.pancs) }
                    var ingredients by rememberSaveable { mutableStateOf(recipeData.ingredientes) }
                    var prepair by rememberSaveable { mutableStateOf(recipeData.preparo) }
                    val image by rememberSaveable { mutableStateOf(recipeData.imagem) }
                    var newImage by rememberSaveable { mutableStateOf<Uri?>(null) }

                    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { result: Uri? ->
                        result?.let { uri ->
                            newImage = uri
                        }
                    }

                    val onCancel = {
                        RecipeWebClient().delete(
                            token = token!!,
                            id = recipeData.id,
                            {
                                navController.navigateComplete(ProfileDestination.route)
                            }, {
                                Log.e("RecipeWebClient.delete()", "failure")
                                navController.navigateComplete(ProfileDestination.route)
                            }
                        )
                    }

                    val onSuccess = {
                        RecipeWebClient().update(
                            token = token!!,
                            id = recipeData.id,
                            RecipeRequest(
                                name,
                                description,
                                pancs,
                                ingredients,
                                prepair,
                                imagem = image,
                                authViewModel.userId
                            ), { res ->
                                if (newImage != null) {
                                    val fileImage = convertContentUriToFile(context, newImage!!)
                                    RecipeWebClient().updateImage(
                                        token = token!!,
                                        id = res.id,
                                        imagem = MultipartBody.Part.createFormData(
                                            "imagem",
                                            fileImage!!.name,
                                            fileImage.asRequestBody()
                                        ), {
                                            navController.navigateComplete(ProfileDestination.route)
                                        }, {
                                            Log.e("RecipeWebClient.updateImage()", "failure")
                                            navController.navigateComplete(ProfileDestination.route)
                                        }
                                    )
                                } else {
                                    navController.navigateComplete(ProfileDestination.route)
                                }
                            }, {
                                Log.e("RecipeWebClient.create()", "failure")
                                navController.navigateComplete(ProfileDestination.route)
                            }
                        )
                    }

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
                        onSuccess = onSuccess,
                        onCancel = onCancel,
                        onEditImage = { getContent.launch(arrayOf("image/*")) },
                        successButtonText = "Salvar",
                        cancelButtonText = "Excluir",
                        navController = navController,
                        imageURL = if(newImage == null) image else newImage.toString(),
                    )
                }
            }
        }
    }
}

fun NavHostController.navigateComplete(route: String) =
    this.navigate(route) {
        launchSingleTop = true
    }
