package com.nelkinda.training

import java.util.Date

enum class ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense(val type: ExpenseType, val amount: Int) {

}

class ExpenseReport {

    private var total = 0
    private var mealExpenses = 0
    fun printReport(expenses: List<Expense>) {

        println("Expenses ${Date()}")

        for (expense in expenses) {
            calculateMealExpense(expense)

            val expenseName = when(expense.type) {
                ExpenseType.DINNER ->  "Dinner"
                ExpenseType.BREAKFAST -> "Breakfast"
                ExpenseType.CAR_RENTAL -> "Car Rental"
            }

            val mealOverExpensesMarker = if (expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000) "X" else " "

            println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            total += expense.amount
        }

        println("Meal expenses: $mealExpenses")
        println("Total expenses: $total")
    }

    private fun calculateMealExpense(expense: Expense) {
        if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
            mealExpenses += expense.amount
        }
    }
}
