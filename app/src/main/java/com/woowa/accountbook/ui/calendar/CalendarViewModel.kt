package com.woowa.accountbook.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.utils.TypeFilter

class CalendarViewModel : ViewModel() {
    val maxDate = 32

    val historyCalendar = MutableLiveData<List<MutableList<History>>>()
    val historyTitle = MutableLiveData("hihi")

    var totIncome = 0
    var totExpenditure = 0

    var year = 2022
    var month = 7

    fun setTotalData(totList: List<History>) {
        totIncome = 0
        totExpenditure = 0
        val list = List<MutableList<History>>(maxDate) { mutableListOf() }
        totList.forEach {
            list[it.day].add(it)
            if (it.type == TypeFilter.INCOME) totIncome += it.price
            else if (it.type == TypeFilter.EXPENDITURE) totExpenditure += it.price
        }
        historyCalendar.value = list
    }
}
