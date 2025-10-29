package com.shubham.expense.repository;

import com.shubham.expense.model.ExpenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Long> {
}
