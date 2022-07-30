package com.woowa.accountbook.domain.model

import me.bytebeats.views.charts.pie.PieChartData

data class CategoryStatisticDto(
    val category: Category,
    val totalPrice: Int,
    val ratio: Float
)

fun CategoryStatisticDto.toSliceOfPieChartData(): PieChartData.Slice {
    return PieChartData.Slice(ratio, category.color)
}
