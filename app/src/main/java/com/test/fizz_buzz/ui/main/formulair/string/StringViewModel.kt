package com.test.fizz_buzz.ui.main.formulair.string

import androidx.lifecycle.ViewModel

class StringViewModel : ViewModel(){

    var str1 : String? = null
    var str2 : String? = null

    fun checkIsNotEmpty() : Boolean{
        return str1?.isNotEmpty() == true && str2?.isNotEmpty() == true
    }

}
