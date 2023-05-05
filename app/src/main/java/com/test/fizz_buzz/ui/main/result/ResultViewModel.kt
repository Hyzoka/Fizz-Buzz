package com.test.fizz_buzz.ui.main.result

import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {

    val listFinal = mutableListOf<String>()

    fun changeMultiples(
        int1: Int,
        int2: Int,
        limit: Int,
        str1: String,
        str2: String
    ): List<String> {
        for (i in 1..limit) {
            when {
                i % int1 == 0 && i % int2 == 0 -> listFinal.add(str1 + str2)
                i % int1 == 0 -> listFinal.add(str1)
                i % int2 == 0 -> listFinal.add(str2)
                else -> listFinal.add(i.toString())
            }
        }
        return listFinal
    }
}