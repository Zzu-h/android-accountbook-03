package com.woowa.accountbook.ui

import androidx.lifecycle.*
import com.woowa.accountbook.domain.model.Expenditure
import com.woowa.accountbook.domain.model.Income
import com.woowa.accountbook.domain.repository.AccountBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AccountBookViewModel @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) : ViewModel() {

    private var _year: MutableLiveData<Int>
    private var _month: MutableLiveData<Int>

    private var _incomeList = MutableStateFlow<List<Income>>(emptyList())
    private var _expenditure = MutableStateFlow<List<Expenditure>>(emptyList())

    val year: LiveData<Int>
    val month: LiveData<Int>

    val incomeList: LiveData<List<Income>> = _incomeList.asLiveData()
    val expenditure: LiveData<List<Expenditure>> = _expenditure.asLiveData()

    init {
        val calendar: Calendar = Calendar.getInstance()
        this._year = MutableLiveData(calendar.get(Calendar.YEAR))
        this._month = MutableLiveData(calendar.get(Calendar.MONTH))

        this.year = _year
        this.month = _month
    }

    private val isNull: Boolean = (_year.value == null || _month.value == null)

    fun plusMonth() {
        if (isNull) return
        adjustYearAndMonth(_year.value!!, _month.value!!.plus(1))
        fetchData()
    }

    fun minusMonth() {
        if (isNull) return
        adjustYearAndMonth(_year.value!!, _month.value!!.minus(1))
        fetchData()
    }

    private fun fetchData() {
        if (isNull) return
        viewModelScope.launch {
            launch {
                accountBookRepository.getIncomeHistory(_year.value!!, _month.value!!)
                    .onSuccess { _incomeList.emit(it) }
                    .onFailure { it.printStackTrace() }
            }
            launch {
                accountBookRepository.getExpenditureHistory(_year.value!!, _month.value!!)
                    .onSuccess { _expenditure.emit(it) }
                    .onFailure { it.printStackTrace() }
            }
        }
    }

    private fun adjustYearAndMonth(year: Int, month: Int) {
        var tmpMonth = month
        var tmpYear = year

        if (month <= 0) {
            tmpMonth = 12
            tmpYear -= 1
        } else if (month >= 13) {
            tmpMonth = 1
            tmpYear += 1
        }

        _year.value = tmpYear
        _month.value = tmpMonth
    }
}