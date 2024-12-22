package com.example.expense_tracker.Repository;
import com.example.expense_tracker.model.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (description, amount, category, date) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate());
    }

    public List<Expense> getAllExpenses() {
        String sql = "SELECT * FROM expenses";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Expense(
                rs.getInt("id"),
                rs.getString("description"),
                rs.getDouble("amount"),
                rs.getString("category"),
                rs.getString("date")
        ));
    }

    public int deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET description = ?, amount = ?, category = ?, date = ? WHERE id = ?";
        return jdbcTemplate.update(sql, expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate(), expense.getId());
    }
}
