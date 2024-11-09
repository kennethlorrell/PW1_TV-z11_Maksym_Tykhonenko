package com.deepdark.lab1.data

data class FuelCompositionResult(
    val dryMassComposition: Map<String, Double>,
    val combustibleMassComposition: Map<String, Double>,
    val lowerHeatWorking: Double,
    val lowerHeatDry: Double,
    val lowerHeatCombustible: Double
)