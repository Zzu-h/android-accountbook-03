package com.woowa.accountbook.ui.statistic.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.CategoryStatisticDto
import com.woowa.accountbook.domain.model.toSliceOfPieChartData
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun GraphComponent(
    dataList: List<CategoryStatisticDto>,
    thickness: Float = 43f,
    horizontalPadding: Dp = 54.dp,
    verticalPadding: Dp = 24.dp,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        PieChart(
            pieChartData = PieChartData(slices = dataList.map { it.toSliceOfPieChartData() }),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            animation = simpleChartAnimation(),
            sliceDrawer = SimpleSliceDrawer(thickness)
        )
    }
}