package com.woowa.accountbook.ui.history.manage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.utils.TypeFilter

class NewHistoryViewModel : ViewModel() {
    val filterType = MutableLiveData(TypeFilter.EXPENDITURE)

    val paymentList = MutableLiveData(emptyList<Payment>())
    val categoryList = MutableLiveData(emptyList<Category>())

    private var incomeCategoryList = emptyList<Category>()
    private var expenditureCategoryList = emptyList<Category>()

    fun setType(type: String) {
        filterType.value = type
        filteringCategory()
    }

    fun setPayment(paymentList: List<Payment>) {
        this.paymentList.value = paymentList
    }

    fun setCategory(categoryList: List<Category>) {
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
}
