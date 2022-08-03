package com.woowa.accountbook.ui.setting.manage.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _manageResult = MutableLiveData<Boolean>()
    val manageResult: LiveData<Boolean> = _manageResult

    fun updatePayment(oldPaymentId: Int) = viewModelScope.launch {
        accountBookManageRepository.updatePayment(Payment(oldPaymentId, _paymentName.value!!))
            .onSuccess { _manageResult.value = it }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }

    fun addPayment() = viewModelScope.launch {
        accountBookManageRepository.addPayment(Payment(-1, _paymentName.value!!))
            .onSuccess { _manageResult.value = it }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }
}