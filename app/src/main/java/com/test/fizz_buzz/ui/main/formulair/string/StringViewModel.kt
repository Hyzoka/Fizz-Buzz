package com.test.fizz_buzz.ui.main.formulair.string

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class StringViewModel : ViewModel(){

    private val _string1 = MutableStateFlow("")
    val string1: StateFlow<String> = _string1

    private val _string2 = MutableStateFlow("")
    val string2: StateFlow<String> = _string2

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    init {
        viewModelScope.launch {
            combine(_string1, _string2) { string1, string2 ->
                val isString1Valid = validateString(string1)
                val isString2Valid = validateString(string2)
                isString1Valid && isString2Valid
            }.collect {
                _isFormValid.value = it
            }
        }
    }

    fun validateString(string: String): Boolean {
        return when {
            string.isEmpty() -> {
                false
            }
            string.length > 10 -> {
                false
            }
            else -> {
                true
            }
        }
    }

    fun setString1(string: String) {
        _string1.value = string
    }

    fun setString2(string: String) {
        _string2.value = string
    }
}
