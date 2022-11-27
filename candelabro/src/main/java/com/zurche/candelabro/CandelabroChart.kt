package com.zurche.candelabro

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zurche.candelabro.model.StockDayValue

@Preview("Main View")
@Composable
fun CandelabroChart(dailyValues: List<StockDayValue>? = null) {
    Text("Hello")
}