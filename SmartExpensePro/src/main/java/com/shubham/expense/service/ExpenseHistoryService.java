package com.shubham.expense.service;

import com.shubham.expense.model.ExpenseHistory;
import com.shubham.expense.model.User;
import com.shubham.expense.repository.ExpenseHistoryRepository;
import com.shubham.expense.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseHistoryService {

    private final ExpenseHistoryRepository expenseHistoryRepository;
    private final UserRepository userRepository;

    public ExpenseHistoryService(ExpenseHistoryRepository expenseHistoryRepository,
                                 UserRepository userRepository) {
        this.expenseHistoryRepository = expenseHistoryRepository;
        this.userRepository = userRepository;
    }

    /**
     * ✅ Fetch all expense history (for admin or debugging)
     */
    public List<ExpenseHistory> getAllHistory() {
        return expenseHistoryRepository.findAll();
    }

    /**
     * ✅ Fetch expense history for a specific user
     */
    public List<ExpenseHistory> getHistoryByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return expenseHistoryRepository.findAll()
                .stream()
                .filter(history -> history.getUser() != null
                        && history.getUser().getEmail().equals(email))
                .toList();
    }

    /**
     * ✅ Fetch all history for a particular expense
     */
    public List<ExpenseHistory> getHistoryByExpenseId(Long expenseId) {
        return expenseHistoryRepository.findAll()
                .stream()
                .filter(history -> history.getExpense() != null
                        && history.getExpense().getId().equals(expenseId))
                .toList();
    }

    /**
     * ✅ Delete all history entries (optional, for cleanup)
     */
    public void clearAllHistory() {
        expenseHistoryRepository.deleteAll();
    }
}