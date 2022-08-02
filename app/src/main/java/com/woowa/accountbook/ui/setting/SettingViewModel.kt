package com.woowa.accountbook.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.utils.TypeFilter

class SettingViewModel : ViewModel() {

    val incomeCategoryList = MutableLiveData<List<Category>>()
    val expenditureCategoryList = MutableLiveData<List<Category>>()
    val paymentList = MutableLiveData<List<Payment>>()

    fun setPaymentList(totList: List<Payment>) {
        this.paymentList.value = totList
    }

    fun setCategoryList(list: List<Category>) {
        val incomeList = mutableListOf<Category>()
        val expenditureList = mutableListOf<Category>()

        list.forEach {
            if (it.type == TypeFilter.INCOME) incomeList.add(it)
            else expenditureList.add(it)
        }

        this.incomeCategoryList.value = incomeList
        this.expenditureCategoryList.value = expenditureList
    }
}
