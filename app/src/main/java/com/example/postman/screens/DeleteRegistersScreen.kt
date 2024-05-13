package com.example.postman.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.postman.navigation.NavScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun DeleteRegistersScreen(navController: NavController){
    var showBooksForm by remember { mutableStateOf(true) }

    Column(horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()) {
        /// TITLE
        Text(text = "Eliminar registro", style = MaterialTheme.typography.titleLarge, fontSize = 35.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Realizar la eliminacion de un registro una acción irreversible",
            style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Elige que registros deseas eliminar",
            style = MaterialTheme.typography.labelMedium,
            fontSize = 20.sp
        )
        Row {
            Button(onClick = { showBooksForm = true }) {
                Text("Mostrar Libros")
            }

            Button(onClick = { showBooksForm = false }) {
                Text("Mostrar Usuarios")
            }
        }

        if (showBooksForm){
            DeleteBooks(navController)
        }else{
            DeleteUsers(navController)
        }
    }
}

@Composable
fun DeleteBooks(navController: NavController){
    var isbn by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /// ID FIELD
        TextField(value = isbn,
            onValueChange = {newValue->
                isbn = newValue },
            label = { Text(text = "ISBN")}
        )

        /// OPERATIONS BUTTONS
        Row {
            ElevatedButton(onClick = {
                navController.navigate(NavScreen.Dashboard.name)
            }) {
                Text(text = "Cancelar")
            }
            Button(onClick = { showAlert = true}) {
                Text(text = "Eliminar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //ALERT
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                title = { Text("Alerta") },
                text = { Text("¿Estás seguro de eliminar este registro? \n"+" Esta acción es irreversible") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showAlert = false
                            deleteBooksFromServer(isbn)
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showAlert = false }
                    ) {
                        Text("No")
                    }
                }
            )
        }
    }
}

@Composable
fun DeleteUsers(navController: NavController){
    var userID by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /// ID FIELD
        TextField(value = userID,
            onValueChange = {newValue->
                userID = newValue },
            label = { Text(text = "User ID")}
        )

        /// OPERATIONS BUTTONS
        Row {
            ElevatedButton(onClick = {
                navController.navigate(NavScreen.Dashboard.name)
            }) {
                Text(text = "Cancelar")
            }
            Button(onClick = { showAlert = true }) {
                Text(text = "Eliminar")
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        //ALERT
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                title = { Text("Alerta") },
                text = { Text("¿Estás seguro de eliminar este registro? \n"+" Esta acción es irreversible") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showAlert = false
                            deleteUsersFromServer(userID)
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showAlert = false }
                    ) {
                        Text("No")
                    }
                }
            )
        }
    }
}


 private fun deleteBooksFromServer(isbn:String): String{
     val db = Firebase.firestore
     val status = "Successfull"
     db.collection("detalles")
         .document(isbn)
         .delete()
         .addOnCompleteListener{
            Log.d("TAG", "Eliminacion exitosa")
         }
     return  status
 }

private fun deleteUsersFromServer(userID:String): String{
    val db = Firebase.firestore
    val status = "Successfull"
    db.collection("credenciales")
        .document(userID)
        .delete()
        .addOnCompleteListener{
            Log.d("TAG", "Eliminacion exitosa")
        }
    return  status
}