package com.test.fizz_buzz.ui.main.formulair.limit

import androidx.lifecycle.ViewModel

class LimitViewModel : ViewModel(){

    var limit : String? = null

    fun checkIsNotEmpty() : Boolean{
        return limit?.isNotEmpty() == true
    }
}
