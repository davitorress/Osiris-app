package com.papaya.osiris.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.papaya.osiris.data.AuthViewModel
import com.papaya.osiris.services.Login
import com.papaya.osiris.services.LoginWebClient
import com.papaya.osiris.ui.pages.*

@Composable
fun OsirisNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = LoginDestination.route,
        modifier = modifier
    ) {
        composable(route = LoginDestination.route) {
            LoginPage(
                onClickLogin = { email, senha ->
                    LoginWebClient().login(
                        Login(email, senha), {
                                Log.i("LoginWebClient", it)
                                navController.navigateComplete(HomeDestination.route)
                        }, {
                            Log.e("LoginWebClient.login()", "failure")
                            navController.navigateComplete(LoginDestination.route)
                        }
                    )
                },
                onClickRegister = {
                    navController.navigateComplete(RegisterDestination.route)
                },
            )
        }
        composable(route = RegisterDestination.route) {
            RegisterPage(
                onClickLogin = {
                    navController.navigateComplete(LoginDestination.route)
                },
                onClickRegister = {
                    navController.navigateComplete(HomeDestination.route)
                },
            )
        }
        composable(route = HomeDestination.route) {
            HomePage(navController, authViewModel)
        }
        composable(route = PancsDestination.route) {
            PancsPage(
                onClickPanc = { pancId ->
                    navController.navigateComplete("${PancDestination.route}/${pancId}")
                },
                navController = navController,
            )
        }
        composable(
            route = PancDestination.route,
            arguments = PancDestination.arguments,
        ) {
            var isFavorite by rememberSaveable { mutableStateOf(false) }

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
                pancId = it.arguments?.getString("pancId"),
                navController = navController,
            )
        }
    }
}

fun NavHostController.navigateComplete(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(route) {
            inclusive = false
        }
    }
