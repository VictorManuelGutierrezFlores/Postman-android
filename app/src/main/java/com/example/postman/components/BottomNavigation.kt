package com.example.postman.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.postman.models.ItemsNavigation
import com.example.postman.navigation.CurrentRoute

@Composable
fun BottomNavigation(
    navController: NavHostController
){
    val menuItems = listOf(
        ItemsNavigation.ItemNavbar,
        ItemsNavigation.ItemNavbar1,
        ItemsNavigation.ItemNavbar2,
        ItemsNavigation.ItemNavbar3
    )

    BottomAppBar {
        NavigationBar {
            menuItems.forEach{item->
                val selected = CurrentRoute(navController) == item.route
                NavigationBarItem(selected = selected,
                    onClick = { navController.navigate(item.route) },
                    icon = { 
                        Icon(imageVector = item.icon,
                            contentDescription = item.title )
                    },
                    label = {
                        Text(text = item.title)
                    }
                )
            }
        }
    }
}