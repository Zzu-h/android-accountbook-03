package com.woowa.accountbook.ui.history.manage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Date
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.TypeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageHistoryViewModel @Inject constructor(
    private val accountBookManageRepository: AccountBookManageRepository
) : ViewModel() {

    private val _date = MutableLiveData(DateUtil.invoke())
    private val _price = MutableLiveData<String>()
    private val _payment = MutableLiveData<String>()
    private val _category = MutableLiveData<String>()
    private val _content = MutableLiveData<String>()

    val date: LiveData<Date> = _date
    val price: LiveData<String> = _price
    val payment: LiveData<String> = _payment
    val category: LiveData<String> = _category
    val content: LiveData<String> = _content
    val filterType = MutableLiveData(TypeFilter.EXPENDITURE)

    private val _buttonEnabled = MutableLiveData(false)
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled

    private var incomeCategoryList = emptyList<Category>()
    private var expenditureCategoryList = emptyList<Category>()

    val paymentList = MutableLiveData(emptyList<Payment>())
    val categoryList = MutableLiveData(emptyList<Category>())

    fun setDate(d: Date) = run { this._date.value = d }
    fun setCategory(category: String) = run { this._category.value = category }
    fun setContent(content: String) = run { this._content.value = content }
    fun setPrice(price: String) = run {
        this._price.value = price
        checkButtonEnabled()
    }

    fun setPayment(payment: String) = run {
        this._payment.value = payment
        checkButtonEnabled()
    }

    fun setType(type: String) {
        filterType.value = type

        _date.value = DateUtil.invoke()
        _price.value = ""
        _payment.value = ""
        _category.value = ""
        _content.value = ""

        filteringCategory()
        checkButtonEnabled()
    }

    fun setPaymentList(paymentList: List<Payment>) {
        this.paymentList.value = paymentList
    }

    fun setCategoryList(categoryList: List<Category>) {
        val incomes = mutableListOf<Category>()
        val expense = mutableListOf<Category>()

        categoryList.forEach { if (it.type == TypeFilter.INCOME) incomes.add(it) else expense.add(it) }
        incomes.removeAt(0)
        expense.removeAt(0)

        incomeCategoryList = incomes.toList()
        expenditureCategoryList = expense.toList()
        filteringCategory()
    }

    private fun filteringCategory() {
        categoryList.value =
            if (filterType.value!! == TypeFilter.INCOME) incomeCategoryList else expenditureCategoryList
    }

    private fun checkButtonEnabled() {
        val pay = payment.value
        val money = price.value
        val isIncome = filterType.value!! == TypeFilter.INCOME

        _buttonEnabled.value =
            (money != null && money.isNotBlank() && (isIncome || pay != null && pay.isNotBlank()))
    }

    private val _manageResult = MutableLiveData<Boolean>()
    val manageResult: LiveData<Boolean> = _manageResult

    fun updateHistory(oldHistoryId: Int) = viewModelScope.launch {
        accountBookManageRepository.updateHistory(
            History(
                id = oldHistoryId,
                content = _content.value,
                payment = getPayment(filterType.value!!),
                price = _price.value!!.toIntForPriceFormat(),
                year = _date.value!!.year,
                month = _date.value!!.month,
                day = _date.value!!.day,
                type = filterType.value!!,
                category = getCategory(filterType.value!!)
            )
        )
            .onSuccess { _manageResult.value = it }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }

    fun addHistory() = viewModelScope.launch {
        accountBookManageRepository.addHistory(
            History(
                id = -1,
                content = _content.value,
                payment = getPayment(filterType.value!!),
                price = _price.value!!.toIntForPriceFormat(),
                year = _date.value!!.year,
                month = _date.value!!.month,
                day = _date.value!!.day,
                type = filterType.value!!,
                category = getCategory(filterType.value!!)
            )
        )
            .onSuccess { _manageResult.value = it }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }

    private fun getPayment(filter: String): Payment {
        val name = _payment.value ?: ""
        return if (filter == TypeFilter.INCOME) {
            Payment(1, name)
        } else {
            var id = 2
            paymentList.value?.forEach {
                if (it.name == name)
                    id = it.id
            }
            Payment(id, name)
        }
    }

    private fun getCategory(filter: String): Category {
        val name = _category.value ?: ""
        if (filter == TypeFilter.INCOME) {
            incomeCategoryList.forEach {
                if (it.title == name) return it
            }
            return Category(1, "미분류", Purple200, filter)
        } else {
            expenditureCategoryList.forEach {
                if (it.title == name) return it
            }
            return Category(2, "미분류", Purple200, filter)
        }

    }

    private fun String.toIntForPriceFormat(): Int {
        return this.replace(",", "").toInt()
    }
}
