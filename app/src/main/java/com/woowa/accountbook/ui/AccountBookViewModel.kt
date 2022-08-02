package com.woowa.accountbook.ui

import androidx.lifecycle.*
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Payment
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

    private val _year: MutableLiveData<Int>
    private val _month: MutableLiveData<Int>

    val year: LiveData<Int>
    val month: LiveData<Int>

    private val _totalHistoryList = MutableStateFlow<List<Account>>(emptyList())
    private val _totalCategoryList = MutableStateFlow<List<Category>>(emptyList())
    private val _totalPaymentList = MutableStateFlow<List<Payment>>(emptyList())

    val totalHistoryList: LiveData<List<Account>> = _totalHistoryList.asLiveData()
    val totalCategoryList: LiveData<List<Category>> = _totalCategoryList.asLiveData()
    val totalPaymentList: LiveData<List<Payment>> = _totalPaymentList.asLiveData()

    init {
        this._year = MutableLiveData(DateUtil.currentYear)
        this._month = MutableLiveData(DateUtil.currentMonth)

        this.year = _year
        this.month = _month
        fetchHistoryList()
        fetchCategoryList()
    }

    private val isNull: Boolean = (_year.value == null || _month.value == null)

    fun plusMonth() {
        if (isNull) return
        adjustYearAndMonth(_year.value!!, _month.value!!.plus(1))
        fetchHistoryList()
    }

    fun minusMonth() {
        if (isNull) return
        adjustYearAndMonth(_year.value!!, _month.value!!.minus(1))
        fetchHistoryList()
    }

    private fun fetchHistoryList() {
        if (isNull) return
        viewModelScope.launch {
            accountBookRepository.getAllHistory(_year.value!!, _month.value!!)
                .onSuccess { _totalHistoryList.emit(it) }
                .onFailure { it.printStackTrace() }
        }
    }

    private fun fetchCategoryList() {
        viewModelScope.launch {
            accountBookRepository.getAllCategory()
                .onSuccess { _totalCategoryList.emit(it) }
                .onFailure { it.printStackTrace() }
        }
    }

    private fun fetchPaymentList() {
        viewModelScope.launch {
            accountBookRepository.getAllPayment()
                .onSuccess { _totalPaymentList.emit(it) }
                .onFailure { it.printStackTrace() }
        }
    }

    private fun adjustYearAndMonth(year: Int, month: Int) {
        val (tmpYear, tmpMonth) = DateUtil.adjustYearAndMonth(year, month)

        _year.value = tmpYear
        _month.value = tmpMonth
    }
}