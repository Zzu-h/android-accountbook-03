package com.woowa.accountbook.ui.setting.manage.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManagePaymentViewModel @Inject constructor(
    private val accountBookManageRepository: AccountBookManageRepository
) : ViewModel() {

    private val _paymentName = MutableLiveData<String>()
    private val _buttonEnabled = MutableLiveData<Boolean>(false)

    val paymentName: LiveData<String> = _paymentName
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled

    fun setPaymentName(name: String) {
        this._paymentName.value = name
        _buttonEnabled.value = name.isNotBlank()
    }

    fun updatePayment(oldPaymentId: Int) {

    }

    fun addPayment() {

    }
}