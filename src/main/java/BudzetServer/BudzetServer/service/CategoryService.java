package BudzetServer.BudzetServer.service;

import BudzetServer.BudzetServer.dto.CategoryDto;
import BudzetServer.BudzetServer.dto.mapper.CategoryMapper;
import BudzetServer.BudzetServer.exception.NotFoundException;
import BudzetServer.BudzetServer.model.Category;
import BudzetServer.BudzetServer.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper)
    {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCaterogies()
    {
        return categoryRepository.findAll();
    }

    public CategoryDto getCategory(Long id)
    {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(()  -> new NotFoundException("Resource not found"));

        return categoryMapper.toDto(category);
    }

}
