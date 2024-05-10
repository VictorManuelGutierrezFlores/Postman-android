@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.postman.screens

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import com.example.postman.components.datePicker
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@ExperimentalMaterial3Api
@Preview
@Composable
fun AddRegisters(){
    // INIT VARIABLES
    var category by remember { mutableStateOf("") }
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
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        /// TITLE
        Text(text = "Añadir producto", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)
        //// CATEGORY FIELD
        category = Categoria()

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
                text = { Text("¿Estás seguro de que quieres enviar el formulario?") },
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

@Composable
fun Categoria(): String {
    var selectedCategory by remember { mutableStateOf("") } // State to track selected category

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Selecciona una categoría:", style = MaterialTheme.typography.bodyMedium)

        Button(
            onClick = { selectedCategory = "Manga" },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (selectedCategory == "Manga") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            )
        ) {
            Text("Manga")
        }

        Button(
            onClick = { selectedCategory = "Comic" },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (selectedCategory == "Comic") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            )
        ) {
            Text("Comic")
        }

        Button(
            onClick = { selectedCategory = "Libro" },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (selectedCategory == "Libro") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            )
        ) {
            Text("Libro")
        }
    }
    return selectedCategory
}