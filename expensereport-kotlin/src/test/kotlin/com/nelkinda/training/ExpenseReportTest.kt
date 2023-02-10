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
    fun `should print report with zero values if empty list is passed`() {

        expenseReport.printReport(emptyList())

        assertEquals("Expenses ${Date()}\nMeal expenses: 0\nTotal expenses: 0\n", outputStreamCaptor.toString())
    }


}