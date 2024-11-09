package com.deepdark.lab1.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.deepdark.lab1.data.recalculateFuelOilComposition
import com.deepdark.lab1.utils.roundTo

@Preview(showBackground = true)
@Composable
fun FuelOilCalculatorPagePreview() {
    FuelOilCalculatorPage()
}

@Composable
fun FuelOilCalculatorPage() {
    var carbon by remember { mutableStateOf("85.5") }
    var hydrogen by remember { mutableStateOf("11.2") }
    var oxygen by remember { mutableStateOf("0.8") }
    var sulfur by remember { mutableStateOf("2.5") }
    var lowerHeatCombustible by remember { mutableStateOf("40.4") }
    var moisture by remember { mutableStateOf("2") }
    var ash by remember { mutableStateOf("0.15") }
    var vanadium by remember { mutableStateOf("333.3") }

    var result by remember { mutableStateOf<Map<String, Any>?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть параметри палива:")

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
            value = oxygen,
            onValueChange = { oxygen = it },
            label = { Text("Кисень (O)") }
        )
        OutlinedTextField(
            value = sulfur,
            onValueChange = { sulfur = it },
            label = { Text("Сірка (S)") }
        )
        OutlinedTextField(
            value = lowerHeatCombustible,
            onValueChange = { lowerHeatCombustible = it },
            label = { Text("Нижня теплота згоряння (Q)") }
        )
        OutlinedTextField(
            value = moisture,
            onValueChange = { moisture = it },
            label = { Text("Вологість (W)") }
        )
        OutlinedTextField(
            value = ash,
            onValueChange = { ash = it },
            label = { Text("Зольність (A)") }
        )
        OutlinedTextField(
            value = vanadium,
            onValueChange = { vanadium = it },
            label = { Text("Вміст ванадію (V)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val carbonValue = carbon.toDoubleOrNull() ?: 0.0
            val hydrogenValue = hydrogen.toDoubleOrNull() ?: 0.0
            val oxygenValue = oxygen.toDoubleOrNull() ?: 0.0
            val sulfurValue = sulfur.toDoubleOrNull() ?: 0.0
            val lowerHeatValue = lowerHeatCombustible.toDoubleOrNull() ?: 0.0
            val moistureValue = moisture.toDoubleOrNull() ?: 0.0
            val ashValue = ash.toDoubleOrNull() ?: 0.0
            val vanadiumValue = vanadium.toDoubleOrNull() ?: 0.0

            result = recalculateFuelOilComposition(
                carbon = carbonValue,
                hydrogen = hydrogenValue,
                oxygen = oxygenValue,
                sulfur = sulfurValue,
                lowerHeatCombustible = lowerHeatValue,
                moisture = moistureValue,
                ash = ashValue,
                vanadium = vanadiumValue
            )
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Склад робочої маси мазуту складатиме::")
            Text("  - Вуглець: ${(it["C"] as Double).roundTo(2)} %")
            Text("  - Водень (H): ${(it["H"] as Double).roundTo(2)} %")
            Text("  - Кисень (O): ${(it["O"] as Double).roundTo(2)} %")
            Text("  - Сірка (S): ${(it["S"] as Double).roundTo(2)} %")
            Text("  - Зола (A): ${(it["A"] as Double).roundTo(2)} %")
            Text("  - Ванадій (V): ${(it["V"] as Double).roundTo(2)} мг/кг")
            Text("  - Нижча теплота згоряння: ${(it["LowerHeatWorking"] as Double).roundTo(2)} МДж/кг")
        }
    }
}
