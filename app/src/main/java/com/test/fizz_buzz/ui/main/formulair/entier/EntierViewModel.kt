package com.test.fizz_buzz.ui.main.formulair.entier

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class EntierViewModel  : ViewModel(){

    private val _entier1 = MutableStateFlow("")
    val entier1: StateFlow<String> = _entier1

    private val _entier2 = MutableStateFlow("")
    val entier2: StateFlow<String> = _entier2

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    init {
        viewModelScope.launch {
            combine(_entier1, _entier2) { entier1, entier2 ->
                val isEntier1Valid = validateEntier(entier1)
                val isEntier2Valid = validateEntier(entier2)
                isEntier1Valid && isEntier2Valid
            }.collect {
                _isFormValid.value = it
            }
        }
    }

     fun validateEntier(entier: String): Boolean {
        return when {
            entier.isEmpty() -> {
                false
            }
            entier.length > 10 -> {
                false
            }
            else -> {
                true
            }
        }
    }

    fun setEntier1(entier: String) {
        _entier1.value = entier
    }

    fun setEntier2(entier: String) {
        _entier2.value = entier
    }
}
