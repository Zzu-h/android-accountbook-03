package com.woowa.accountbook.ui.setting.manage.category

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageCategoryViewModel @Inject constructor(
    private val accountBookManageRepository: AccountBookManageRepository
) : ViewModel() {

    private val _categoryName = MutableLiveData<String>()
    private val _categoryColor = MutableLiveData<Color>()
    private val _buttonEnabled = MutableLiveData<Boolean>(false)

    val categoryName: LiveData<String> = _categoryName
    val categoryColor: LiveData<Color> = _categoryColor
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled

    fun setCategoryName(name: String) {
        this._categoryName.value = name
        _buttonEnabled.value = name.isNotBlank()
    }

    fun setCategoryColor(color: Color) {
        this._categoryColor.value = color
    }

    private val _manageResult = MutableLiveData<Boolean>()
    val manageResult: LiveData<Boolean> = _manageResult

    fun updateCategory(filter: String, oldCategoryId: Int) = viewModelScope.launch {
        accountBookManageRepository.updateCategory(
            Category(
                id = oldCategoryId,
                title = _categoryName.value!!,
                color = _categoryColor.value!!,
                type = filter
            )
        ).onSuccess { _manageResult.value = it }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }

    fun addCategory(filter: String) = viewModelScope.launch {
        accountBookManageRepository.addCategory(
            Category(
                id = -1,
                title = _categoryName.value!!,
                color = _categoryColor.value!!,
                type = filter
            )
        ).onSuccess { _manageResult.value = it }
            .onFailure {
                it.printStackTrace()
                _manageResult.value = false
            }
    }
}