package BudzetServer.BudzetServer.controller;

import BudzetServer.BudzetServer.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService)
//    {
//        this.categoryService = categoryService;
//    }

//    @GetMapping
//    public List<Category> getAllCategories()
//    {
//        return categoryService.getAllCaterogies();
//    }

//    @GetMapping("/{id}")
//    public CategoryDto getCategory(@PathVariable Long id)
//    {
//        return categoryService.getCategory(id);
//    }
}
