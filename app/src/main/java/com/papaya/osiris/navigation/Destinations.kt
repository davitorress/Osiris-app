package com.papaya.osiris.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface Destination {
    val route: String
}

object LoginDestination: Destination {
    override val route = "login"
}

object RegisterDestination: Destination {
    override val route = "register"
}

object HomeDestination: Destination {
    override val route = "home"
}

object PancsDestination: Destination {
    override val route = "pancs"
}

object PancDestination: Destination {
    override val route = "panc"
    const val pancIdArg = "panc_id"
    val routeWithArgs = "${route}/{${pancIdArg}}"
    val arguments = listOf(
        navArgument(pancIdArg) { type = NavType.StringType },
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "android-app://androidx.navigation/$route/{$pancIdArg}"},
    )
}

object RecipesDestination: Destination {
    override val route = "recipes"
}

object RecipeDestination: Destination {
    override val route = "recipe"
    const val recipeIdArg = "recipe_id"
    val routeWithArgs = "${route}/{${recipeIdArg}}"
    val arguments = listOf(
        navArgument(recipeIdArg) { type = NavType.StringType },
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "android-app://androidx.navigation/$route/{$recipeIdArg}"},
    )
}

object RecipeFormDestination: Destination {
    override val route = "recipe_form"
    const val formIdArg = "form_id"
    val routeWithArgs = "${route}/{${formIdArg}}"
    val arguments = listOf(
        navArgument(formIdArg) { type = NavType.StringType },
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "android-app://androidx.navigation/$route/{$formIdArg}"},
    )
}

object ProfileDestination: Destination {
    override val route = "profile"
    const val profileIdArg = "profile_id"
    val routeWithArgs = "${route}/{${profileIdArg}}"
    val arguments = listOf(
        navArgument(profileIdArg) { type = NavType.StringType },
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "android-app://androidx.navigation/$route/{$profileIdArg}"},
    )
}

val OsirisDestinations = listOf(
    LoginDestination,
    RegisterDestination,
    HomeDestination,
    PancsDestination,
    PancDestination,
    RecipesDestination,
    RecipeDestination,
    RecipeFormDestination,
    ProfileDestination,
)
