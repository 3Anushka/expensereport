package com.nelkinda.training

import com.nelkinda.training.model.Expense
import com.nelkinda.training.model.ExpenseType
import java.util.Date
import kotlin.collections.MutableMap as MutableMap



class ExpenseReport {

    private var total = 0
    private var mealExpenses = 0
    fun printReport(expenses: List<Expense>, date : Date=Date()) {

        println("Expenses ${date}")

        for (expense in expenses) {
            calculateMealExpense(expense)

            val expenseName = getExpenseName(expense)

            val mealOverExpensesMarker = getMealOverExpensesMarker(expenseName,expense.amount)


            println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            total += expense.amount
        }

        println("Meal expenses: $mealExpenses")
        println("Total expenses: $total")
    }

    private fun getMealOverExpensesMarker(expenseName:String,amount: Int): String {

        val expenseLimit : MutableMap<String,Int> = mutableMapOf()

        expenseLimit["Dinner"] = 5000
        expenseLimit["Breakfast"] = 1000

        if(expenseLimit[expenseName]==null)
            return " "

        if (amount > expenseLimit[expenseName]!!)
            return "X"

        return " "
    }

    private fun getExpenseName(expense: Expense) = when (expense.type) {
        ExpenseType.DINNER -> "Dinner"
        ExpenseType.BREAKFAST -> "Breakfast"
        ExpenseType.CAR_RENTAL -> "Car Rental"
    }

    private fun calculateMealExpense(expense: Expense) {
        if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
            mealExpenses += expense.amount
        }
    }
}
