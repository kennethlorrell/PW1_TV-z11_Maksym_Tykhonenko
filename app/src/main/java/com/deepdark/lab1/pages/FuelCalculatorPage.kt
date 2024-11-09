package com.deepdark.lab1.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.deepdark.lab1.data.FuelCompositionResult
import com.deepdark.lab1.data.calculateFuelProperties

@Preview(showBackground = true)
@Composable
fun FuelInputScreenPreview() {
    FuelInputScreen()
}

@Composable
fun FuelInputScreen() {
    var hydrogen by remember { mutableStateOf("1.9") }
    var carbon by remember { mutableStateOf("21.1") }
    var sulfur by remember { mutableStateOf("2.6") }
    var nitrogen by remember { mutableStateOf("0.2") }
    var oxygen by remember { mutableStateOf("7.1") }
    var moisture by remember { mutableStateOf("53") }
    var ash by remember { mutableStateOf("14.1") }

    var result by remember { mutableStateOf<FuelCompositionResult?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть значення компонентів палива (%)")

        OutlinedTextField(
            value = hydrogen,
            onValueChange = { hydrogen = it },
            label = { Text("Водень (H)") }
        )
        OutlinedTextField(
            value = carbon,
            onValueChange = { carbon = it },
            label = { Text("Вуглець (C)") }
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
            val hydrogenValue = hydrogen.toDoubleOrNull() ?: 0.0
            val carbonValue = carbon.toDoubleOrNull() ?: 0.0
            val sulfurValue = sulfur.toDoubleOrNull() ?: 0.0
            val nitrogenValue = nitrogen.toDoubleOrNull() ?: 0.0
            val oxygenValue = oxygen.toDoubleOrNull() ?: 0.0
            val moistureValue = moisture.toDoubleOrNull() ?: 0.0
            val ashValue = ash.toDoubleOrNull() ?: 0.0

            result = calculateFuelProperties(
                hydrogen = hydrogenValue,
                carbon = carbonValue,
                sulfur = sulfurValue,
                nitrogen = nitrogenValue,
                oxygen = oxygenValue,
                moisture = moistureValue,
                ash = ashValue
            )
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Для палива з компонентним складом:")
            Text("Коефіцієнти сухої маси: ${it.dryMassComposition}")
            Text("Коефіцієнти горючої маси: ${it.combustibleMassComposition}")
            Text("Нижня теплота згоряння (Робоча): ${it.lowerHeatWorking} МДж/кг")
            Text("Нижня теплота згоряння (Суха): ${it.lowerHeatDry} МДж/кг")
            Text("Нижня теплота згоряння (Горюча): ${it.lowerHeatCombustible} МДж/кг")
        }
    }
}
