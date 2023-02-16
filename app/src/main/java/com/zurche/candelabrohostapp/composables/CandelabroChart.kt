package com.zurche.candelabrohostapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zurche.candelabrohostapp.model.CandleData

private const val VALUE_MAGNIFIER = 10

@Preview("Main View")
@Composable
fun CandelabroChart(
    dailyValues: List<CandleData> = mockCandleData
) {
    Row {
        val highestMaxDailyValue: Double = dailyValues.maxOf { it.high }
        for (dayValue in dailyValues) {
            val offset = (highestMaxDailyValue - dayValue.high) * VALUE_MAGNIFIER
            Candle(dayValue, offset.dp)
        }
    }
}

@Composable
private fun Candle(dayValue: CandleData, topOffset: Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = topOffset, start = 2.dp, end = 2.dp, bottom = 1.dp)
    ) {
        with(dayValue) {
            val isGreenDay = close > open

            UpperShadow(isGreenDay, high, close, open)

            RealBody(isGreenDay, close, open)

            LowerShadow(isGreenDay, open, low, close)
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
    val upperShadowHeight = if (isGreenDay) maxValue - closeValue else maxValue - openValue
    Shadow(upperShadowHeight)
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
            .padding(5.dp)
    )
}

@Composable
private fun LowerShadow(
    isGreenDay: Boolean,
    openValue: Double,
    minValue: Double,
    closeValue: Double
) {
    val lowerShadowHeight = if (isGreenDay) openValue - minValue else closeValue - minValue
    Shadow(lowerShadowHeight)
}

@Composable
private fun Shadow(shadowHeight: Double) {
    val finalHeight = shadowHeight * VALUE_MAGNIFIER
    Divider(
        color = Color.Black,
        modifier = Modifier
            .height(finalHeight.dp)
            .width(1.dp)
    )
}
