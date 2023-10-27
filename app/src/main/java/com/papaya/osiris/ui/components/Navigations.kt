package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papaya.osiris.ui.theme.*

class Link(val text: String, val icon: ImageVector, val activeIcon: ImageVector)

val userLinks: List<Link> = listOf(
    Link("Home", Icons.Outlined.Home, Icons.Filled.Home),
    Link("PANCs", Icons.Outlined.Eco, Icons.Filled.Eco),
    Link("Receitas", Icons.Outlined.MenuBook, Icons.Filled.MenuBook),
    Link("Perfil", Icons.Outlined.Person, Icons.Filled.Person),
)

@Composable
fun NavBar(
    links: List<Link>,
    modifier: Modifier = Modifier
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = White,
        contentColor = DarkGreen,
        tonalElevation = 24.dp,
        modifier = modifier.wrapContentHeight().fillMaxWidth()
    ) {
        links.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    if(selectedItem == index) Icon(item.activeIcon, contentDescription = null)
                    else Icon(item.icon, contentDescription = null)
                },
                label = { Text(text = item.text, style = MaterialTheme.typography.bodySmall) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkGreen,
                    unselectedIconColor = DarkGreen,
                    indicatorColor = Gray,
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
        NavBar(userLinks)
    }
}