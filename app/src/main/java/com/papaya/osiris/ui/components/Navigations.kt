package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.papaya.osiris.navigation.*
import com.papaya.osiris.ui.theme.*

class Link(val text: String, val icon: ImageVector, val activeIcon: ImageVector, val route: String)

val userLinks: List<Link> = listOf(
    Link("Home", Icons.Outlined.Home, Icons.Filled.Home, HomeDestination.route),
    Link("PANCs", Icons.Outlined.Eco, Icons.Filled.Eco, PancsDestination.route),
    Link("Receitas", Icons.Outlined.Book, Icons.Filled.MenuBook, RecipesDestination.route),
    Link("Perfil", Icons.Outlined.Person, Icons.Filled.Person, ProfileDestination.route),
)

@Composable
fun NavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = White,
        contentColor = DarkGreen,
        tonalElevation = 24.dp,
        modifier = modifier.wrapContentHeight().fillMaxWidth()
    ) {
        userLinks.forEach { item ->
            NavigationBarItem(
                selected = true,
                onClick = {
                    navController.navigateComplete(item.route)
                },
                icon = {
                    if(item.route === navController.currentDestination?.route) Icon(item.activeIcon, contentDescription = null)
                    else Icon(item.icon, contentDescription = null)
                },
                label = { Text(text = item.text, style = MaterialTheme.typography.bodySmall) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkGreen,
                    unselectedIconColor = DarkGreen,
                    indicatorColor = White,
                    selectedTextColor = DarkGreen,
                    unselectedTextColor = DarkGreen
                )
            )
        }
    }
}

@Preview(widthDp = 400, heightDp = 400)
@Composable
fun NavBarPreview() {
    OsirisTheme {
        NavBar(NavHostController(LocalContext.current))
    }
}