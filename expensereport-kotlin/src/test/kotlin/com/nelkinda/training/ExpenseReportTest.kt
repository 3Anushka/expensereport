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


    @Test
    fun `it should print report when all expenses are passed and check if total and meal expenses are different`() {

        val expense1 = Expense(ExpenseType.DINNER, 10)
        val expense2 = Expense(ExpenseType.BREAKFAST, 10)
        val expense3 = Expense(ExpenseType.CAR_RENTAL, 10)
        val allExpenses = arrayListOf<Expense>()
        allExpenses.add(expense1)
        allExpenses.add(expense2)
        allExpenses.add(expense3)


        expenseReport.printReport(allExpenses)

        val expectedOutput = "Expenses ${Date()}\nDinner\t10\t \n" +
                "Breakfast\t10\t \n" +
                "Car Rental\t10\t \nMeal expenses: 20\nTotal expenses: 30\n"

        val actualOutput = outputStreamCaptor.toString()

        checkAssertion(expectedOutput, actualOutput)
    }

    @Test
    fun `it should print X infront of dinner report if amount exceed 5000`() {

        val expense1 = Expense(ExpenseType.DINNER, 5001)
        val expense2 = Expense(ExpenseType.BREAKFAST, 10)
        val allExpenses = arrayListOf<Expense>()
        allExpenses.add(expense1)
        allExpenses.add(expense2)


        expenseReport.printReport(allExpenses)

        val expectedOutput = "Expenses ${Date()}\nDinner\t5001\tX\n" +
                "Breakfast\t10\t \nMeal expenses: 5011\nTotal expenses: 5011\n"
        val actualOutput = outputStreamCaptor.toString()

        checkAssertion(expectedOutput, actualOutput)
    }



    @Test
    fun `it should print X infront of breakfast report if amount exceed 5000`() {

        val expense1 = Expense(ExpenseType.DINNER, 500)
        val expense2 = Expense(ExpenseType.BREAKFAST, 1001)
        val allExpenses = arrayListOf<Expense>()
        allExpenses.add(expense1)
        allExpenses.add(expense2)


        expenseReport.printReport(allExpenses)

        val expectedOutput = "Expenses ${Date()}\nDinner\t500\t \n" +
                "Breakfast\t1001\tX\nMeal expenses: 1501\nTotal expenses: 1501\n"
        val actualOutput = outputStreamCaptor.toString()

        checkAssertion(expectedOutput, actualOutput)
    }


    private fun checkAssertion(expectedOutput: String, actualOutput: String) {
        assertEquals(expectedOutput, actualOutput)
    }


}