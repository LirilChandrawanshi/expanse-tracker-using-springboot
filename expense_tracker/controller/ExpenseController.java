package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public String addExpense(@RequestBody Expense expense) {
        int result = expenseService.addExpense(expense);
        return result > 0 ? "Expense added successfully" : "Error adding expense";
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable int id) {
        int result = expenseService.deleteExpense(id);
        return result > 0 ? "Expense deleted successfully" : "Error deleting expense";
    }

    @PutMapping
    public String updateExpense(@RequestBody Expense expense) {
        int result = expenseService.updateExpense(expense);
        return result > 0 ? "Expense updated successfully" : "Error updating expense";
    }
}
