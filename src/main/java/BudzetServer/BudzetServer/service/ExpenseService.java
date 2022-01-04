package BudzetServer.BudzetServer.service;

import BudzetServer.BudzetServer.dto.AddExpenseDto;
import BudzetServer.BudzetServer.dto.ExpenseDto;
import BudzetServer.BudzetServer.exception.NotFoundException;
import BudzetServer.BudzetServer.model.Expense;
import BudzetServer.BudzetServer.model.Category;
import BudzetServer.BudzetServer.repository.ExpenseRepository;
import BudzetServer.BudzetServer.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(item -> getExpense(item.getId()))
                .collect(Collectors.toList());
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ExpenseDto getExpense(Long id) {
        return expenseRepository
                .findById(id)
                .map(c -> ExpenseDto.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .price(c.getPrice())
                        .type(c.getCategory().getName())
                        .build())
                .orElseThrow(() -> new NotFoundException("Resource not found"));

    }

    @Transactional
    public ExpenseDto addExpense(AddExpenseDto addExpenseDto) {
        Expense expense = new Expense();
        Category category = new Category();

        category.setId(addExpenseDto.getCategoryId());
        expense.setName(addExpenseDto.getName());
        expense.setPrice(addExpenseDto.getPrice());
        expense.setCategory(category);

        Expense result = expenseRepository.save(expense);

        return getExpense(result.getId());
    }

    public void deleteElement(Long id) {
        expenseRepository.findById(id).ifPresent(expenseRepository::delete);
    }

    public ExpenseDto changeElement(AddExpenseDto addExpenseDto, Long id) {

        return expenseRepository.findById(id)
                .map(e -> {
                    Category category = new Category();
                    category.setId(addExpenseDto.getCategoryId());

                    e.setName(addExpenseDto.getName());
                    e.setPrice(addExpenseDto.getPrice());
                    e.setCategory(category);

                    return expenseRepository.save(e);
                })
                .map(e -> getExpense(e.getId()))
                .orElseThrow();
    }

}
