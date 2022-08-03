package com.woowa.accountbook.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.utils.TypeFilter

class HistoryViewModel : ViewModel() {
    val maxDate = 32

    private var totHistory = emptyList<History>()
    val historyCalendar = MutableLiveData<List<MutableList<History>>>()
    val historyTitle = MutableLiveData("hihi")

    val incomeFilter = MutableLiveData(true)
    val expenditureFilter = MutableLiveData(true)
    val emptyFlag = MutableLiveData(true)

    var totIncome = 0
    var totExpenditure = 0

    var year = 2022
    var month = 7

    fun setTotalData(totList: List<History>) {
        totIncome = 0
        totExpenditure = 0
        totHistory = totList
        filteringData()
    }

    fun filteringIncomeData() {
        val flag = !(incomeFilter.value ?: true)
        incomeFilter.value = flag
        filteringData()
    }

    fun filteringExpenditureData() {
        val flag = !(expenditureFilter.value ?: true)
        expenditureFilter.value = flag
        filteringData()
    }

    private fun filteringData() {
        val incomeFlag = incomeFilter.value!!
        val expenditureFlag = expenditureFilter.value!!
        var tmpEmptyFlag = true

        val list = List<MutableList<History>>(maxDate) { mutableListOf() }
        totHistory.forEach {
            if (it.type == TypeFilter.INCOME && incomeFlag) {
                totIncome += it.price
                list[it.day].add(it)
                tmpEmptyFlag = false
            } else if (it.type == TypeFilter.EXPENDITURE && expenditureFlag) {
                totExpenditure += it.price
                list[it.day].add(it)
                tmpEmptyFlag = false
            }
        }
        historyCalendar.value = list
        emptyFlag.value = tmpEmptyFlag
    }

    val trashList = MutableLiveData<List<History>>(emptyList())
    val editFlag = MutableLiveData(false)

    fun branchOffTrash(history: History) {
        trashList.value?.let {
            if (it.contains(history)) removeTrashList(history)
            else addTrashList(history)
        }
    }

    private fun addTrashList(history: History) {
        trashList.value?.let {
            val list = it.toMutableList()
            list.add(history)
            trashList.value = list.toList()
            editFlag.value = list.isNotEmpty()
        }
    }

    private fun removeTrashList(history: History) {
        trashList.value?.let {
            val list = it.toMutableList()
            list.remove(history)
            trashList.value = list.toList()
            editFlag.value = list.isNotEmpty()
        }
    }
}
