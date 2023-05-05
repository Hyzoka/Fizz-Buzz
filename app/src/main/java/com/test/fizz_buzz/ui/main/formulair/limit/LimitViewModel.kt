package com.test.fizz_buzz.ui.main.formulair.limit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LimitViewModel : ViewModel() {

    private val _limit = MutableStateFlow("")
    val limit: StateFlow<String> = _limit

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    init {
        viewModelScope.launch {
            _limit.collect {
                _isFormValid.value = validateLimit(_limit.value)

            }
        }
    }

     fun validateLimit(limit: String): Boolean {
        return when {
            limit.isEmpty() -> {
                false
            }
            else -> {
                true
            }
        }
    }

    fun setLimit(limit: String) {
        _limit.value = limit
    }
}
