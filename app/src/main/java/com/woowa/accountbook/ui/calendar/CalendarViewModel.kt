package com.woowa.accountbook.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.utils.TypeFilter

class CalendarViewModel : ViewModel() {
    val maxDate = 32

    val historyCalendar = MutableLiveData<List<Pair<Int, Int>>>()
    val historyTitle = MutableLiveData("hihi")

    val totalHistory = MutableLiveData<Pair<Int, Int>>()

    var year = 2022
    var month = 7

    private val list = MutableList(maxDate) { Pair(0, 0) }

    fun setTotalData(totList: List<History>) {
        var totIncome = 0
        var totExpenditure = 0

        val income = MutableList(maxDate) { 0 }
        val expenditure = MutableList(maxDate) { 0 }
        totList.forEach {
            if (it.type == TypeFilter.INCOME) {
                totIncome += it.price
                income[it.day] += it.price
            } else if (it.type == TypeFilter.EXPENDITURE) {
                expenditure[it.day] += it.price
                totExpenditure += it.price
            }
        }

        repeat(maxDate) { idx ->
            list[idx] = Pair(income[idx], expenditure[idx])
        }
        totalHistory.value = Pair(totIncome, totExpenditure)
        historyCalendar.value = list
    }
}
