package com.zurche.candelabro.model

import java.util.Date

data class StockDayValue(
    val date: Date?,
    val openValue: Double,
    val closeValue: Double,
    val maxValue: Double,
    val minValue: Double
)
