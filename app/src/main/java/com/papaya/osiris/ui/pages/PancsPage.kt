package com.papaya.osiris.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.White
import com.papaya.osiris.viewmodel.AuthViewModel
import com.papaya.osiris.viewmodel.PancViewModel
import com.papaya.osiris.viewmodel.UserViewModel

@Composable
fun PancsPage(
    navController: NavHostController,
    pancViewModel: PancViewModel,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
) {
    val userId = authViewModel.userId
    val token by authViewModel.token.observeAsState()
    val user by userViewModel.user.observeAsState(null)
    val pancs by pancViewModel.pancs.observeAsState(null)

    var search by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        userId?.let { userViewModel.fetch(token!!, userId, {}) }
        pancViewModel.fetch({})
    }

    // TODO: Implement conversion of pancs to have isFavorite property

    Scaffold(
        containerColor = White,
        bottomBar = { NavBar(navController) },
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
                    .verticalScroll(rememberScrollState())
                    .padding(PaddingValues(22.dp))
            ) {
                SearchInput(
                    text = search,
                    placeholder = "Busque por pancs",
                    onTextChange = { text -> search = text }
                )

                // TODO: Implement search
//                if (search.isNotBlank()) {
//                    val searchItems = pancs?.filter { panc -> panc.nome.contains(search, ignoreCase = true) }
//
//                    searchItems?.let {
//                        PancsListSection(
//                            title = "PANCs encontradas",
//                            items = searchItems
//                        )
//                    }
//                }

                // TODO: Implement pancs list section
//                PancsListSection(
//                    title = "PANCs",
//                    items = pancs
//                )
            }
        }
    }
}