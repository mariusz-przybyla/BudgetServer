package BudzetServer.BudzetServer.controller;

import BudzetServer.BudzetServer.dto.AddExpenseDto;
import BudzetServer.BudzetServer.dto.ExpenseDto;
import BudzetServer.BudzetServer.model.Category;
import BudzetServer.BudzetServer.model.User;
import BudzetServer.BudzetServer.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/categories")
    public List<Category> getAllCategories()
    {
        return expenseService.getAllCategories();
    }

    @GetMapping("/expense")
    public List<ExpenseDto> getAllExpenses(Authentication authentication)
    {
        User user = (User)authentication.getPrincipal();
        return expenseService.getAllExpenses(user);
    }

    @GetMapping("/expense/{id}")
    public ExpenseDto getExpense(@PathVariable Long id, Authentication authentication)
    {
        User user = (User)authentication.getPrincipal();
        return expenseService.getExpense(id, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expense")
    public ExpenseDto addExpense(@RequestBody AddExpenseDto addExpenseDto, Authentication authentication)
    {
        User user = (User)authentication.getPrincipal();
        return expenseService.addExpense(addExpenseDto, user);
    }

    @ResponseStatus(HttpStatus.GONE)
    @DeleteMapping("/expense/{id}")
    public void deleteElement(@PathVariable Long id, Authentication authentication)
    {
        User user = (User)authentication.getPrincipal();
        expenseService.deleteElement(id, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/expense/{id}")
    public ExpenseDto changeElement(@Valid @RequestBody AddExpenseDto addExpenseDto, @PathVariable Long id, Authentication authentication)
    {
        Long test = id;
        User user = (User)authentication.getPrincipal();
        return expenseService.changeElement(addExpenseDto, id, user);
    }
}
