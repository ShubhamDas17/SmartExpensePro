package com.shubham.expense.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

@Builder
@Table(name = "expense_history")
public class ExpenseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String action; // e.g. CREATED, UPDATED, DELETED

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String payload; // JSON or text data

    @Column(name = "action_at")
    private LocalDateTime actionAt = LocalDateTime.now();
}
