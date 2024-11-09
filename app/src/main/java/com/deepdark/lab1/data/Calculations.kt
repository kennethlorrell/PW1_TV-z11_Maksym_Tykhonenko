package com.deepdark.lab1.data

fun calculateDryMassCoefficient(waterContent: Double): Double {
    return 100 / (100 - waterContent)
}

fun calculateCombustibleMassCoefficient(waterContent: Double, ashContent: Double): Double {
    return 100 / (100 - waterContent - ashContent)
}

fun calculateDryMassComposition(componentPercentage: Double, dryMassCoefficient: Double): Double {
    return componentPercentage * dryMassCoefficient
}

fun calculateCombustibleMassComposition(componentPercentage: Double, combustibleMassCoefficient: Double): Double {
    return componentPercentage * combustibleMassCoefficient
}

fun calculateLowerHeatOfCombustion(
    hydrogen: Double,
    carbon: Double,
    oxygen: Double,
    sulfur: Double,
    moisture: Double
): Double {
    return (339 * carbon + 1030 * hydrogen - 108.8 * (oxygen - sulfur) - 25 * moisture) / 1000
}

fun calculateHeatForDryMass(workingHeat: Double, moisture: Double): Double {
    return (workingHeat + 0.025 * moisture) * (100 / (100 - moisture))
}

fun calculateHeatForCombustibleMass(workingHeat: Double, moisture: Double, ash: Double): Double {
    return (workingHeat + 0.025 * moisture) * (100 / (100 - moisture - ash))
}

fun calculateFuelProperties(
    hydrogen: Double,
    carbon: Double,
    sulfur: Double,
    nitrogen: Double,
    oxygen: Double,
    moisture: Double,
    ash: Double
): FuelCompositionResult {
    val dryMassCoefficient = calculateDryMassCoefficient(moisture)
    val combustibleMassCoefficient = calculateCombustibleMassCoefficient(moisture, ash)

    val dryMassComposition = mapOf(
        "H" to calculateDryMassComposition(hydrogen, dryMassCoefficient),
        "C" to calculateDryMassComposition(carbon, dryMassCoefficient),
        "S" to calculateDryMassComposition(sulfur, dryMassCoefficient),
        "N" to calculateDryMassComposition(nitrogen, dryMassCoefficient),
        "O" to calculateDryMassComposition(oxygen, dryMassCoefficient),
        "A" to calculateDryMassComposition(ash, dryMassCoefficient)
    )

    val combustibleMassComposition = mapOf(
        "H" to calculateCombustibleMassComposition(hydrogen, combustibleMassCoefficient),
        "C" to calculateCombustibleMassComposition(carbon, combustibleMassCoefficient),
        "S" to calculateCombustibleMassComposition(sulfur, combustibleMassCoefficient),
        "N" to calculateCombustibleMassComposition(nitrogen, combustibleMassCoefficient),
        "O" to calculateCombustibleMassComposition(oxygen, combustibleMassCoefficient)
    )

    val lowerHeatWorking = calculateLowerHeatOfCombustion(hydrogen, carbon, oxygen, sulfur, moisture)

    val lowerHeatDry = calculateHeatForDryMass(lowerHeatWorking, moisture)
    val lowerHeatCombustible = calculateHeatForCombustibleMass(lowerHeatWorking, moisture, ash)

    return FuelCompositionResult(
        dryMassComposition = dryMassComposition,
        combustibleMassComposition = combustibleMassComposition,
        lowerHeatWorking = lowerHeatWorking,
        lowerHeatDry = lowerHeatDry,
        lowerHeatCombustible = lowerHeatCombustible
    )
}
