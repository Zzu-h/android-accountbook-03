package com.woowa.accountbook.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.model.AccountType

class CalendarViewModel : ViewModel() {
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
            if (it.type == AccountType.INCOME) totIncome += it.price
            else if (it.type == AccountType.EXPENDITURE) totExpenditure += it.price
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