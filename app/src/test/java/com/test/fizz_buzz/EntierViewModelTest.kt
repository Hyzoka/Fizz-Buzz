package com.test.fizz_buzz

import com.test.fizz_buzz.ui.main.formulair.entier.EntierViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before

import org.junit.Test

@ExperimentalCoroutinesApi
class EntierViewModelTest {

    // System under test
    private lateinit var viewModel: EntierViewModel
    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setupViewModel() {
        Dispatchers.setMain(testDispatcher)
        viewModel = EntierViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun setEntier1_validInput_setsEntier1() = runTest {
        // Given
        val expected = "123"

        // When
        viewModel.setEntier1(expected)

        // Then
        val result = viewModel.entier1.value
        assertThat(result, `is`(expected))
    }

    @Test
    fun setEntier2_validInput_setsEntier2() = runTest {
        // Given
        val expected = "456"

        // When
        viewModel.setEntier2(expected)

        // Then
        val result = viewModel.entier2.value
        assertThat(result, `is`(expected))
    }

    @Test
    fun isFormValid_entier1Empty_returnsFalse() = runTest {
        // Given
        viewModel.setEntier1("")
        viewModel.setEntier2("456")

        // When
        val result = viewModel.isFormValid.value

        // Then
        assertThat(result, `is`(false))
    }

    @Test
    fun isFormValid_entier2Empty_returnsFalse() = runTest {
        // Given
        viewModel.setEntier1("123")
        viewModel.setEntier2("")

        // When
        val result = viewModel.isFormValid.value

        // Then
        assertThat(result, `is`(false))
    }

    @Test
    fun isFormValid_entier1NotNumeric_returnsFalse() = runTest {
        // Given
        viewModel.setEntier1("abc")
        viewModel.setEntier2("456")

        // When
        val result = viewModel.isFormValid.value

        // Then
        assertThat(result, `is`(false))
    }

    @Test
    fun isFormValid_entier2NotNumeric_returnsFalse() = runTest {
        // Given
        viewModel.setEntier1("123")
        viewModel.setEntier2("def")

        // When
        val result = viewModel.isFormValid.value

        // Then
        assertThat(result, `is`(false))
    }

    @Test
    fun isFormValid_validInput_returnsTrue() = runTest {
        // Given
        viewModel.setEntier1("123")
    }
}

