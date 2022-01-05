package BudzetServer.BudzetServer.controller;

import BudzetServer.BudzetServer.dto.AddExpenseDto;
import BudzetServer.BudzetServer.dto.ExpenseDto;
import BudzetServer.BudzetServer.model.Category;
import BudzetServer.BudzetServer.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@Controller
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/categories")
    public List<Category> getAllCategories()
    {
        return expenseService.getAllCategories();
    }

    @GetMapping("/expense")
    public List<ExpenseDto> getAllExpenses()
    {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/expense/{id}")
    public ExpenseDto getExpense(@PathVariable Long id)
    {
        return expenseService.getExpense(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expense")
    public ExpenseDto addExpense(@RequestBody AddExpenseDto addExpenseDto)
    {
        return expenseService.addExpense(addExpenseDto);
    }

    @ResponseStatus(HttpStatus.GONE)
    @DeleteMapping("/expense/{id}")
    public void deleteElement(@PathVariable Long id)
    {
        expenseService.deleteElement(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/expense/{id}")
    public ExpenseDto changeElement(@RequestBody AddExpenseDto addExpenseDto, Long id)
    {
        return expenseService.changeElement(addExpenseDto, id);
    }
}
