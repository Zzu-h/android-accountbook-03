package com.woowa.accountbook.ui

import androidx.lifecycle.*
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.repository.AccountBookRepository
import com.woowa.accountbook.utils.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountBookViewModel @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) : ViewModel() {

    private var _year: MutableLiveData<Int>
    private var _month: MutableLiveData<Int>

    private var _totalList = MutableStateFlow<List<Account>>(emptyList())

    val year: LiveData<Int>
    val month: LiveData<Int>

    val totalList: LiveData<List<Account>> = _totalList.asLiveData()

    init {
        this._year = MutableLiveData(DateUtil.currentYear)
        this._month = MutableLiveData(DateUtil.currentMonth)

        this.year = _year
        this.month = _month
        fetchData()
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
            accountBookRepository.getAllHistory(_year.value!!, _month.value!!)
                .onSuccess { _totalList.emit(it) }
                .onFailure { it.printStackTrace() }
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