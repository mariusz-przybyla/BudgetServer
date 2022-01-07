package BudzetServer.BudzetServer.repository;

import BudzetServer.BudzetServer.model.Expense;
import BudzetServer.BudzetServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUser(User user);

    Optional<Expense> findByIdAndUser(Long id, User user);
}
