package com.shubham.expense.controller;

import com.shubham.expense.model.ExpenseHistory;
import com.shubham.expense.repository.ExpenseHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class ExpenseHistoryController {

    private final ExpenseHistoryRepository repo;

    public ExpenseHistoryController(ExpenseHistoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseHistory>> getAllHistory() {
        return ResponseEntity.ok(repo.findAll());
    }
}