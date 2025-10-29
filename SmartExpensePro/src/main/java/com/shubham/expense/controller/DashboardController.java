package com.shubham.expense.controller;

import com.shubham.expense.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();

        summary.put("message", "✅ Welcome to SmartExpensePro Dashboard!");
        summary.put("totalExpense", dashboardService.getTotalExpense());
        summary.put("thisMonthExpense", dashboardService.getThisMonthExpense());
        summary.put("categorySummary", dashboardService.getCategorySummary());

        return ResponseEntity.ok(summary);
    }
}
