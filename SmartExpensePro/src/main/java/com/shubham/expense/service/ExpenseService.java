package com.shubham.expense.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shubham.expense.model.Expense;
import com.shubham.expense.model.ExpenseHistory;
import com.shubham.expense.model.User;
import com.shubham.expense.repository.ExpenseHistoryRepository;
import com.shubham.expense.repository.ExpenseRepository;
import com.shubham.expense.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseHistoryRepository expenseHistoryRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, ExpenseHistoryRepository expenseHistoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseHistoryRepository = expenseHistoryRepository;
        objectMapper.registerModule(new JavaTimeModule());
    }

    // ✅ Add a new expense
    public Expense addExpense(String userEmail, Expense expense) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        expense.setUser(user);
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }

        Expense saved = expenseRepository.save(expense);
        saveHistory(user, saved, "CREATED");
        return saved;
    }

    // ✅ Get all expenses for a user
    public List<Expense> getUserExpenses(String userEmail) {
        return expenseRepository.findAll()
                .stream()
                .filter(exp -> exp.getUser() != null && userEmail.equals(exp.getUser().getEmail()))
                .toList();
    }

    // ✅ Get a single expense by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));
    }

    // ✅ Delete an expense
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));

        saveHistory(expense.getUser(), expense, "DELETED");
        expenseRepository.delete(expense);
    }

    // ✅ Update an expense
    public Expense updateExpense(Long id, Expense newExpense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));

        existingExpense.setAmount(newExpense.getAmount());
        existingExpense.setCategory(newExpense.getCategory());
        existingExpense.setNote(newExpense.getNote());
        existingExpense.setDate(newExpense.getDate());

        Expense updated = expenseRepository.save(existingExpense);
        saveHistory(existingExpense.getUser(), updated, "UPDATED");
        return updated;
    }

    // ✅ Log history
    private void saveHistory(User user, Expense expense, String action) {
        try {
            String payload = objectMapper.writeValueAsString(expense);
            ExpenseHistory history = ExpenseHistory.builder()
                    .user(user)
                    .expense(expense)
                    .action(action)
                    .payload(payload)
                    .build();
            expenseHistoryRepository.save(history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
