package com.test.fizz_buzz

import com.test.fizz_buzz.ui.main.formulair.string.StringViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class StringViewModelTest {

    private lateinit var viewModel: StringViewModel
    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setupViewModel() {
        Dispatchers.setMain(testDispatcher)
        viewModel = StringViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    
    @Test
    fun `setString1 should update the value of string1`() = runTest {
        viewModel.setString1("Test")
        val result = viewModel.string1.first()
        assertEquals(result,"Test")
    }

    @Test
    fun `setString2 should update the value of string2`() = runTest {
        viewModel.setString2("Test")
        val result = viewModel.string2.first()
        assertEquals(result,"Test")
    }

    @Test
    fun `validateEntier should return false when the input is empty`() {
        val result = viewModel.validateString("")
        assertFalse(result)
    }

    @Test
    fun `validateEntier should return true when the input is not empty and length is less than or equal to 10`() {
        val result = viewModel.validateString("test")
        assertTrue(result)
    }

    @Test
    fun `isFormValid should return false when string1 is invalid`() = runTest {
        viewModel.setString1("")
        viewModel.setString2("test")
        assertFalse(viewModel.isFormValid.value)
    }

    @Test
    fun `isFormValid should return false when string2 is invalid`() = runTest{
        viewModel.setString1("test")
        viewModel.setString2("")
        assertFalse(viewModel.isFormValid.first())
    }

    @Test
    fun `isFormValid should return false when both string1 and string2 are invalid`() = runTest {
        viewModel.setString1("")
        viewModel.setString2("")
        assertFalse(viewModel.isFormValid.first())
    }
}