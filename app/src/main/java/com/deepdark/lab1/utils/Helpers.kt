package com.deepdark.lab1.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.round

@Composable
fun SuperscriptText(text: String, superscript: String, color: Color = Color.Black) {
    Text(
        buildAnnotatedString {
            append(text)
            withStyle(style = SpanStyle(fontSize = 10.sp, baselineShift = BaselineShift.Superscript)) {
                append(superscript)
            }
        },
        color = color,
        textAlign = TextAlign.Left
    )
}

fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return round(this * factor) / factor
}

fun Map<String, Double>.roundToNearestDecimal(decimals: Int): Map<String, Double> {
    return this.mapValues { entry -> entry.value.roundTo(decimals) }
}