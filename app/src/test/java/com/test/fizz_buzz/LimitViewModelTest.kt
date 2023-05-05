package com.test.fizz_buzz

import com.test.fizz_buzz.ui.main.formulair.limit.LimitViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LimitViewModelTest {

    private lateinit var limitViewModel: LimitViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        limitViewModel = LimitViewModel()
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `setLimit - validateLimit - with empty limit - should return false`() =
        testScope.runBlockingTest {
            // Given
            val limit = ""

            // When
            limitViewModel.setLimit(limit)

            // Then
            Assert.assertFalse(limitViewModel.isFormValid.value)
        }

    @Test
    fun `setLimit - validateLimit - with non-empty limit - should return true`() =
        testScope.runBlockingTest {
            // Given
            val limit = "10"

            // When
            limitViewModel.setLimit(limit)

            // Then
            Assert.assertTrue(limitViewModel.isFormValid.value)
        }
}
