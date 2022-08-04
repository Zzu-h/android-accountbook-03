package com.woowa.accountbook.ui.statistic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.CategoryStatistic
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.utils.TypeFilter

class StatisticViewModel : ViewModel() {

    private var loadedData = mutableListOf<Pair>()

    val statisticData = MutableLiveData<MutableList<CategoryStatistic>>()
    val statisticTitle = MutableLiveData("hihi")
    val totalHistory = MutableLiveData<Int>()

    private var totExpenditure = 0

    var year = 2022
    var month = 7

    fun setCategoryData(totList: List<Category>) {
        totExpenditure = 0
        val list = mutableListOf<Pair>()
        totList.forEach {
            if (it.type == TypeFilter.EXPENDITURE)
                list.add(Pair(it, 0))
        }
        loadedData = list
    }

    fun setHistoryData(data: List<History>) {
        if (loadedData.isNotEmpty()) {
            data.forEach {
                if (it.type == TypeFilter.EXPENDITURE) {
                    loadedData[findIdx(it.category.id)].price += it.price
                    totExpenditure += it.price
                }
            }

            val statisticList = mutableListOf<CategoryStatistic>()
            loadedData.forEach {
                if (it.price > 0)
                    statisticList.add(
                        CategoryStatistic(
                            it.category,
                            it.price,
                            (it.price.toFloat() / totExpenditure.toFloat())
                        )
                    )
            }
            statisticData.value = statisticList
            totalHistory.value = totExpenditure
            initialize()
        }
    }

    private fun initialize() {
        loadedData.forEach { it.price = 0 }
        totExpenditure = 0
    }

    private fun findIdx(categoryId: Int): Int {
        loadedData.forEachIndexed { idx, it ->
            if (it.category.id == categoryId)
                return idx
        }
        return 0
    }

    private data class Pair(
        val category: Category,
        var price: Int,
    )
}
