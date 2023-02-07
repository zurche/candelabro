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

@Preview("Main View")
@Composable
fun CandelabroChart(
    dailyValues: List<StockDayValue> = listOf(
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
) {

    Row {
        for (dayValue in dailyValues) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(20.dp)
            ) {
                val isGreenDay = dayValue.closeValue > dayValue.openValue

                val valueMagnifier = 10
                val upperShadowHeight =
                    if (isGreenDay) {
                        (dayValue.maxValue - dayValue.closeValue) * valueMagnifier
                    } else {
                        (dayValue.maxValue - dayValue.openValue) * valueMagnifier
                    }


                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .height(upperShadowHeight.dp)
                        .width(1.dp)
                )

                val realBodyHeight =
                    if (isGreenDay) {
                        (dayValue.closeValue - dayValue.openValue) * valueMagnifier
                    } else {
                        (dayValue.openValue - dayValue.closeValue) * valueMagnifier
                    }
                Box(
                    modifier = Modifier
                        .height(realBodyHeight.dp)
                        .border(1.dp, Color.Black)
                        .background(if (isGreenDay) Color.Green else Color.Red)
                        .padding(8.dp)
                )


                val lowerShadowHeight =
                    if (isGreenDay) {
                        (dayValue.openValue - dayValue.minValue) * valueMagnifier
                    } else {
                        (dayValue.closeValue - dayValue.minValue) * valueMagnifier
                    }

                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .height(lowerShadowHeight.dp)
                        .width(1.dp)
                )
            }
        }
    }
}