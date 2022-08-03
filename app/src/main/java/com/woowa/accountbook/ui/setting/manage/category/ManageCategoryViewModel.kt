package com.woowa.accountbook.ui.setting.manage.category

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun updateCategory(filter: String, oldCategoryId: Int) {

    }

    fun addCategory() {

    }
}