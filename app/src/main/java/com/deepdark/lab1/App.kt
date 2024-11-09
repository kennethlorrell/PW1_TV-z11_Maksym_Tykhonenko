package com.deepdark.lab1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.deepdark.lab1.pages.FuelCalculatorPage
import com.deepdark.lab1.pages.FuelOilCalculatorPage

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var selectedPage by remember { mutableStateOf("Завдання 1") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(selectedPage) },
                actions = {
                    TextButton(onClick = { selectedPage = "Завдання 1" }) {
                        Text("Завдання 1")
                    }
                    TextButton(onClick = { selectedPage = "Завдання 2" }) {
                        Text("Завдання 2")
                    }
                }
            )
        },
        content = { contentPaddings ->
            Column(modifier = Modifier.padding(contentPaddings)) {
                when (selectedPage) {
                    "Завдання 1" -> FuelCalculatorPage()
                    "Завдання 2" -> FuelOilCalculatorPage()
                }
            }
        }
    )
}
