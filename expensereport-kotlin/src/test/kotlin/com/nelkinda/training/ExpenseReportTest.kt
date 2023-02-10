package com.nelkinda.training

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*


class ExpenseReportTest {


    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    private lateinit var expenseReport: ExpenseReport

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
        expenseReport = ExpenseReport()
    }

    @Test
    fun `it should print report with zero values if empty list is passed`() {

        expenseReport.printReport(emptyList())

        assertEquals("Expenses ${Date()}\nMeal expenses: 0\nTotal expenses: 0\n", outputStreamCaptor.toString())
    }

    @Test
    fun `it should print report when one dinner expense is passed`() {

        val expense1 = Expense(ExpenseType.DINNER, 10)
        val allExpenses = arrayListOf<Expense>()
        allExpenses.add(expense1)


        expenseReport.printReport(allExpenses)

        val expectedOutput = "Expenses ${Date()}\nDinner\t10\t \nMeal expenses: 10\nTotal expenses: 10\n"
        val actualOutput = outputStreamCaptor.toString()

        checkAssertion(expectedOutput, actualOutput)
    }

    private fun checkAssertion(expectedOutput: String, actualOutput: String) {
        assertEquals(expectedOutput, actualOutput)
    }


}