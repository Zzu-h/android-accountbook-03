package com.woowa.accountbook.ui.history.manage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Date
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.TypeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageHistoryViewModel @Inject constructor(
    accountBookManageRepository: AccountBookManageRepository
) : ViewModel() {

    private val _date = MutableLiveData<Date>()
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
}
