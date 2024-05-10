package com.example.postman.models

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Update
import com.example.postman.navigation.NavScreen

sealed class ItemsNavigation(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    object ItemNavbar: ItemsNavigation(
        Icons.Filled.Home,
        "Dashboard",
        NavScreen.Dashboard.name
    )
    object ItemNavbar1: ItemsNavigation(
        Icons.Filled.Add,
        "Añadir",
        NavScreen.AddRegisters.name
    )
    object ItemNavbar2: ItemsNavigation(
        Icons.Filled.Update,
        "Actualizar",
        NavScreen.UpdateRegisters.name
    )
    object  ItemNavbar3: ItemsNavigation(
        Icons.Filled.Delete,
        "Borrar",
        NavScreen.DeleteRegisters.name
    )
}