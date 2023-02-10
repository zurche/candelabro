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
        val isGreenDay = dayValue.closeValue > dayValue.openValue

        UpperShadow(isGreenDay, dayValue)

        RealBody(isGreenDay, dayValue)

        LowerShadow(isGreenDay, dayValue)
    }
}

@Composable
private fun RealBody(
    isGreenDay: Boolean,
    dayValue: StockDayValue
) {
    val realBodyHeight =
        if (isGreenDay) {
            (dayValue.closeValue - dayValue.openValue) * VALUE_MAGNIFIER
        } else {
            (dayValue.openValue - dayValue.closeValue) * VALUE_MAGNIFIER
        }
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
    dayValue: StockDayValue
) {
    val lowerShadowHeight =
        if (isGreenDay) {
            (dayValue.openValue - dayValue.minValue) * VALUE_MAGNIFIER
        } else {
            (dayValue.closeValue - dayValue.minValue) * VALUE_MAGNIFIER
        }

    Divider(
        color = Color.Black,
        modifier = Modifier
            .height(lowerShadowHeight.dp)
            .width(1.dp)
    )
}

@Composable
private fun UpperShadow(
    isGreenDay: Boolean,
    dayValue: StockDayValue
) {
    val upperShadowHeight =
        if (isGreenDay) {
            (dayValue.maxValue - dayValue.closeValue) * VALUE_MAGNIFIER
        } else {
            (dayValue.maxValue - dayValue.openValue) * VALUE_MAGNIFIER
        }


    Divider(
        color = Color.Black,
        modifier = Modifier
            .height(upperShadowHeight.dp)
            .width(1.dp)
    )
}