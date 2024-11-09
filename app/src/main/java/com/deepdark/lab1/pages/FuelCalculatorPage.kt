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
import com.deepdark.lab1.utils.roundTo
import com.deepdark.lab1.utils.roundToNearestDecimal

@Preview(showBackground = true)
@Composable
fun FuelCalculatorPagePreview() {
    FuelCalculatorPage()
}

@Composable
fun FuelCalculatorPage() {
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
        Text("Введіть значення компонентів палива:")

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
            Text("Для палива з компонентним складом: ${it.workingMassComposition.roundToNearestDecimal(2)}")

            Text("Коефіцієнт переходу від робочої до сухої маси: ${it.dryMassCoefficient.roundTo(2)}")
            Text("Коефіцієнт переходу від робочої до горючої маси: ${it.combustibleMassCoefficient.roundTo(2)}")

            Text("Склад сухої маси палива: ${it.dryMassComposition.roundToNearestDecimal(2)}")
            Text("Склад горючої маси палива: ${it.combustibleMassComposition.roundToNearestDecimal(2)}")

            Text("Нижня теплота згоряння для робочої маси: ${it.lowerHeatWorking.roundTo(2)} МДж/кг")
            Text("Нижня теплота згоряння для сухої маси: ${it.lowerHeatDry.roundTo(2)} МДж/кг")
            Text("Нижня теплота згоряння для горючої маси: ${it.lowerHeatCombustible.roundTo(2)} МДж/кг")
        }
    }
}
