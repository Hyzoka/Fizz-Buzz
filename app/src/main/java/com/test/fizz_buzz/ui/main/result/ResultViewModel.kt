package com.test.fizz_buzz.ui.main.result

import androidx.lifecycle.ViewModel

class ResultViewModel: ViewModel() {

    val listFinal = mutableListOf<String>()

    fun changeMultiples(int1 : Int, int2 : Int,limit : Int, str1 : String, str2 : String) : List<String>{
        for (i in 0..limit){
            if (checkMultiples(int1,i)){
                listFinal.add(str1)
            }
            else if (checkMultiples(int2,i)){
                listFinal.add(str2)
            }
            else if (checkMultiples(int2,i) && checkMultiples(int2,i)){
                listFinal.add("$str1$str2")
            }
            else{
                listFinal.add(i.toString())
            }
        }
        listFinal.removeAt(0)
        return listFinal
    }

    fun checkMultiples(mutiple : Int, target : Int) : Boolean {
        return target % mutiple == 0
    }
}