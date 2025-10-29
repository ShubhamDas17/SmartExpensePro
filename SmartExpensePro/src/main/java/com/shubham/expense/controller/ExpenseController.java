package com.shubham.expense.controller;

import com.shubham.expense.model.Expense;
import com.shubham.expense.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(Principal principal) {
        String userEmail = principal.getName();
        return ResponseEntity.ok(expenseService.getUserExpenses(userEmail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Principal principal) {
        String userEmail = principal.getName();
        return ResponseEntity.ok(expenseService.addExpense(userEmail, expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense newExpense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, newExpense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
