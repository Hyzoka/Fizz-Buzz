package com.test.fizz_buzz

import com.test.fizz_buzz.ui.main.result.ResultViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ResultViewModelTest {

    private lateinit var viewModel: ResultViewModel

    @Before
    fun setUp() {
        viewModel = ResultViewModel()
    }

    @Test
    fun `when int1 is 2, int2 is 3, limit is 10, str1 is Fizz and str2 is Buzz, then the resulting list should be correct`() {
        val expectedList = listOf(
            "1",
            "Fizz",
            "Buzz",
            "Fizz",
            "5",
            "FizzBuzz",
            "7",
            "Fizz",
            "Buzz",
            "Fizz"
        )

        val result = viewModel.changeMultiples(2, 3, 10, "Fizz", "Buzz")

        assertEquals(expectedList, result)
    }

    @Test
    fun `when int1 is 4, int2 is 7, limit is 15, str1 is Fizz and str2 is Buzz, then the resulting list should be correct`() {
        val expectedList = listOf(
            "1",
            "2",
            "3",
            "Fizz",
            "5",
            "6",
            "Buzz",
            "Fizz",
            "9",
            "10",
            "11",
            "Fizz",
            "13",
            "Buzz",
            "15"
        )

        val result = viewModel.changeMultiples(4, 7, 15, "Fizz", "Buzz")

        assertEquals(expectedList, result)
    }
}
