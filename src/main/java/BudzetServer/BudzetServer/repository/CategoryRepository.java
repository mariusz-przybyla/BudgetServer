package BudzetServer.BudzetServer.repository;

import BudzetServer.BudzetServer.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
