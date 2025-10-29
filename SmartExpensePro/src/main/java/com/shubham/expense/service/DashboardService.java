package com.shubham.expense.service;

import com.shubham.expense.model.Expense;
import com.shubham.expense.repository.ExpenseRepository;
import com.shubham.expense.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public DashboardService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // ✅ Get total expense
    public double getTotalExpense() {
        return expenseRepository.findAll().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // ✅ Get this month's total expense
    public double getThisMonthExpense() {
        LocalDate now = LocalDate.now();
        return expenseRepository.findAll().stream()
                .filter(exp -> exp.getDate() != null &&
                        exp.getDate().getMonth() == now.getMonth() &&
                        exp.getDate().getYear() == now.getYear())
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // ✅ Get expense summary by category
    public Map<String, Double> getCategorySummary() {
        return expenseRepository.findAll().stream()
                .filter(exp -> exp.getCategory() != null)
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }
}
