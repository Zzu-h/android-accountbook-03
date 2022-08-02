package com.woowa.accountbook.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.utils.TypeFilter

class HistoryViewModel : ViewModel() {
    val maxDate = 32

    val historyCalendar = MutableLiveData<List<MutableList<Account>>>()
    val historyTitle = MutableLiveData("hihi")

    val incomeFilter = MutableLiveData(true)
    val expenditureFilter = MutableLiveData(true)

    var totIncome = 0
    var totExpenditure = 0

    var year = 2022
    var month = 7

    fun setTotalData(totList: List<Account>) {
        totIncome = 0
        totExpenditure = 0
        val list = List<MutableList<Account>>(maxDate) { mutableListOf() }
        totList.forEach {
            list[it.day].add(it)
            if (it.type == TypeFilter.INCOME) totIncome += it.price
            else if (it.type == TypeFilter.EXPENDITURE) totExpenditure += it.price
        }
        historyCalendar.value = list
    }

    fun filteringIncomeData() {
        val flag = !(incomeFilter.value ?: true)

        incomeFilter.value = flag
    }

    fun filteringExpenditureData() {
        val flag = !(expenditureFilter.value ?: true)

        expenditureFilter.value = flag
    }
}
