package com.zurche.candelabro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zurche.candelabro.model.StockDayValue

private val defaultValues = listOf(
    StockDayValue(
        null,
        6.0,
        8.0,
        10.0,
        4.0
    ),
    StockDayValue(
        null,
        8.0,
        3.0,
        10.0,
        1.0
    )
)
private const val VALUE_MAGNIFIER = 10

@Preview("Main View")
@Composable
fun CandelabroChart(
    dailyValues: List<StockDayValue> = defaultValues
) {
    Row {
        for (dayValue in dailyValues) {
            Candle(dayValue)
        }
    }
}

@Composable
private fun Candle(dayValue: StockDayValue) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(20.dp)
    ) {
        with(dayValue) {
            val isGreenDay = closeValue > openValue

            UpperShadow(isGreenDay, maxValue, closeValue, openValue)

            RealBody(isGreenDay, closeValue, openValue)

            LowerShadow(isGreenDay, openValue, minValue, closeValue)
        }
    }
}

@Composable
private fun UpperShadow(
    isGreenDay: Boolean,
    maxValue: Double,
    closeValue: Double,
    openValue: Double
) {
    val upperShadowHeight =
        if (isGreenDay) {
            maxValue - closeValue
        } else {
            maxValue - openValue
        } * VALUE_MAGNIFIER

    Divider(
        color = Color.Black,
        modifier = Modifier
            .height(upperShadowHeight.dp)
            .width(1.dp)
    )
}

@Composable
private fun RealBody(
    isGreenDay: Boolean,
    closeValue: Double,
    openValue: Double
) {
    val realBodyHeight =
        if (isGreenDay) {
            closeValue - openValue
        } else {
            openValue - closeValue
        } * VALUE_MAGNIFIER

    Box(
        modifier = Modifier
            .height(realBodyHeight.dp)
            .border(1.dp, Color.Black)
            .background(if (isGreenDay) Color.Green else Color.Red)
            .padding(8.dp)
    )
}

@Composable
private fun LowerShadow(
    isGreenDay: Boolean,
    openValue: Double,
    minValue: Double,
    closeValue: Double
) {
    val lowerShadowHeight =
        if (isGreenDay) {
            openValue - minValue
        } else {
            closeValue - minValue
        } * VALUE_MAGNIFIER

    Divider(
        color = Color.Black,
        modifier = Modifier
            .height(lowerShadowHeight.dp)
            .width(1.dp)
    )
}
