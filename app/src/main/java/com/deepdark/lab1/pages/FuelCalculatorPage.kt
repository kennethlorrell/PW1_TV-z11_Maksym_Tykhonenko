package com.deepdark.lab1.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun FuelInputScreenPreview() {
    FuelInputScreen()
}

@Composable
fun FuelInputScreen() {
    var carbon by remember { mutableStateOf("") }
    var hydrogen by remember { mutableStateOf("") }
    var sulfur by remember { mutableStateOf("") }
    var nitrogen by remember { mutableStateOf("") }
    var oxygen by remember { mutableStateOf("") }
    var moisture by remember { mutableStateOf("") }
    var ash by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть значення компонентів палива (%)")

        OutlinedTextField(
            value = carbon,
            onValueChange = { carbon = it },
            label = { Text("Вуглець (C)") }
        )
        OutlinedTextField(
            value = hydrogen,
            onValueChange = { hydrogen = it },
            label = { Text("Водень (H)") }
        )
        OutlinedTextField(
            value = sulfur,
            onValueChange = { sulfur = it },
            label = { Text("Сірка (S)") }
        )
        OutlinedTextField(
            value = nitrogen,
            onValueChange = { nitrogen = it },
            label = { Text("Азот (N)") }
        )
        OutlinedTextField(
            value = oxygen,
            onValueChange = { oxygen = it },
            label = { Text("Кисень (O)") }
        )
        OutlinedTextField(
            value = moisture,
            onValueChange = { moisture = it },
            label = { Text("Волога (W)") }
        )
        OutlinedTextField(
            value = ash,
            onValueChange = { ash = it },
            label = { Text("Зола (A)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
        }) {
            Text("Розрахувати")
        }
    }
}
