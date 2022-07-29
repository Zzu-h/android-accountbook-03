package com.woowa.accountbook.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.model.AccountType

class HistoryViewModel : ViewModel() {
    val maxDate = 32

    val historyCalendar =
        List<MutableLiveData<MutableList<Account>>>(maxDate) { MutableLiveData(mutableListOf()) }
    val historyTitle = MutableLiveData("hihi")

    var totIncome = 0
    var totExpenditure = 0

    fun setTotalData(totList: List<Account>) {
        totIncome = 0
        totExpenditure = 0
        historyCalendar.forEach { it.value = mutableListOf() }
        totList.forEach {
            historyCalendar[it.day].value?.add(it)
            if (it.type == AccountType.INCOME) totIncome += it.price
            else if (it.type == AccountType.EXPENDITURE) totExpenditure += it.price
        }
    }
}
