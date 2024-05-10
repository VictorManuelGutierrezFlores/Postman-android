package com.example.postman.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DeleteRegisters(){
    var ISBN by remember { mutableStateOf("") }
    /// STATES
    var showAlert by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /// TITLE
        Text(text = "Eliminar registro", style = MaterialTheme.typography.titleLarge, fontSize = 35.sp)
        Text(text = "Realizar la eliminacion de un registro una acción irreversible",
            style = MaterialTheme.typography.bodyMedium)

        /// ISBN FIELD
        TextField(value = ISBN,
            onValueChange = {newValue->
                ISBN = newValue },
            label = { Text(text = "ISBN")}
        )

        /// OPERATIONS BUTTONS
        Row {
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Cancelar")
            }
            Button(onClick = { showAlert = true }) {
                Text(text = "Agregar")
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
                            // Submit the form data
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