@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.postman.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.postman.models.Books
import com.example.postman.models.User
import com.example.postman.navigation.NavScreen
import com.example.postman.screens.components.datePicker
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase

@ExperimentalMaterial3Api
@Composable
fun AddRegisters(navController: NavController){
    // INIT VARIABLES
    var isbn by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var discountValue by remember { mutableStateOf("") }
    /// STATES
    var state by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        /// TITLE
        Text(text = "Añadir producto", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)

        /// ISBN FIELD
        OutlinedTextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        /// AUTHOR FIELD
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        /// TITLE FIELD
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // PUBLISHER FIELD
        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Editorial") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // DATE FIELD
        selectedDate = datePicker()

        Spacer(modifier = Modifier.height(8.dp))
        /// PRICE FIELD

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))
        // DISCOUNT FIELD
        Text(text = "¿Aplica descuento?")
        Row(Modifier.selectableGroup()) {
            RadioButton(
                selected = state,
                onClick = { state = true },
                modifier = Modifier.semantics { contentDescription = "SI" }
            )
            RadioButton(
                selected = !state,
                onClick = { state = false },
                modifier = Modifier.semantics { contentDescription = "NO" }
            )
        }
        Descuento(showDiscountField = state, discount = discountValue,
            onDiscountChange = {newValue ->
                discountValue = newValue
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            ElevatedButton(onClick = { navController.navigate(NavScreen.Dashboard.name) }) {
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
                text = { Text("¿Estás seguro de que quieres enviar el formulario?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showAlert = false
                            addOnServer(isbn,author,title,publisher,selectedDate,price,discountValue)
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
fun Descuento(
    showDiscountField: Boolean, // Flag to control whether to show the discount field
    discount: String? = null, // Initial value for the discount field
    onDiscountChange: (String) -> Unit // Callback to update the discount value
) {
    Column {
        if (showDiscountField) {
            OutlinedTextField(
                value = discount ?: "",
                onValueChange = onDiscountChange,
                label = { Text("Descuento") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        } else {
            Text("No se aplica descuento")
        }
    }
}

private fun addOnServer(isbn:String, autor:String,titulo:String,editorial:String, fecha:String,precio:String,descuento:String){
    val db = Firebase.firestore
    db.collection("detalles")
        .document(isbn).set(
            hashMapOf("autor" to autor,
                "titulo" to titulo,
                "editorial" to editorial,
                "fecha" to fecha,
                "precio" to precio,
                "descuenot" to descuento)
        )
}