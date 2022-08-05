package com.woowa.accountbook.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import com.woowa.accountbook.utils.TypeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val accountBookManageRepository: AccountBookManageRepository
) : ViewModel() {
    val maxDate = 32

    private var totHistory = emptyList<History>()
    val historyCalendar = MutableLiveData<List<MutableList<History>>>()
    val historyTitle = MutableLiveData("hihi")

    val incomeFilter = MutableLiveData(true)
    val expenditureFilter = MutableLiveData(true)
    val emptyFlag = MutableLiveData(true)


    private val _totIncome = MutableLiveData(0)
    private val _totExpenditure = MutableLiveData(0)

    val totIncome: LiveData<Int> = _totIncome
    val totExpenditure: LiveData<Int> = _totExpenditure

    var year = 2022
    var month = 7

    fun setTotalData(totList: List<History>) {
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

        var tmpIncome = 0
        var tmpExpenditure = 0

        val list = List<MutableList<History>>(maxDate) { mutableListOf() }
        totHistory.forEach {
            if (it.type == TypeFilter.INCOME) {
                tmpIncome += it.price
                if (incomeFlag) list[it.day].add(it)
                tmpEmptyFlag = false
            } else if (it.type == TypeFilter.EXPENDITURE) {
                tmpExpenditure += it.price
                if (expenditureFlag) list[it.day].add(it)
                tmpEmptyFlag = false
            }
        }
        historyCalendar.value = list
        emptyFlag.value = tmpEmptyFlag
        _totIncome.value = tmpIncome
        _totExpenditure.value = tmpExpenditure
    }

    val trashList = MutableLiveData<List<History>>(emptyList())
    val editFlag = MutableLiveData(false)

    fun branchOffTrash(history: History) {
        trashList.value?.let {
            if (it.contains(history)) removeTrashList(history)
            else addTrashList(history)
        }
    }

    fun clearTrashList() {
        trashList.value = emptyList()
        editFlag.value = false
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

    private val _manageResult = MutableLiveData<Boolean>()
    val manageResult: LiveData<Boolean> = _manageResult

    fun removeHistoryList() = viewModelScope.launch {
        accountBookManageRepository.removeHistoryList(historyList = trashList.value!!)
            .onSuccess {
                _manageResult.value = it
                editFlag.value = false
            }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }
}
