package com.test.fizz_buzz.ui.main

import androidx.lifecycle.ViewModel
import com.test.fizz_buzz.model.Entiers
import com.test.fizz_buzz.model.Strings

class MainViewModel : ViewModel() {

     var entier1 = 0
     var entier2 = 0

     var str1 = ""
     var str2 = ""

     var limit = 0

     fun getEntiers() = Entiers(entier1,entier2)
     fun getStrings() = Strings(str1,str2)
     fun getRange() = limit
}