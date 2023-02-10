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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zurche.candelabro.model.StockDayValue

private val defaultValues = listOf(
    StockDayValue(
        108.50,
        109.39,
        109.94,
        106.90
    ),
    StockDayValue(
        110.67,
        113.21,
        113.21,
        109.69
    ),
    StockDayValue(
        111.47,
        110.71,
        113.14,
        110.37
    ),
    StockDayValue(
        110.17,
        109.87,
        110.43,
        108.53
    ),
    StockDayValue(
        109.30,
        111.63,
        112.12,
        108.86
    ),
    StockDayValue(
        112.20,
        111.78,
        113.33,
        110.29
    )
)
private const val VALUE_MAGNIFIER = 10

@Preview("Main View")
@Composable
fun CandelabroChart(
    dailyValues: List<StockDayValue> = defaultValues
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
private fun Candle(dayValue: StockDayValue, topOffset: Dp) {
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
    val upperShadowHeight =
        if (isGreenDay) {
            maxValue - closeValue
        } else {
            maxValue - openValue
        }

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
    val lowerShadowHeight =
        if (isGreenDay) {
            openValue - minValue
        } else {
            closeValue - minValue
        }

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
