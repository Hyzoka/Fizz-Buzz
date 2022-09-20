package com.test.fizz_buzz.ui.main.formulair.entier

import androidx.lifecycle.ViewModel

class EntierViewModel  : ViewModel(){

    var entier1 : String? = null
    var entier2 : String? = null

    fun checkIsNotEmpty() : Boolean{
        return entier1?.isNotEmpty() == true &&  entier2?.isNotEmpty() == true
    }

}
