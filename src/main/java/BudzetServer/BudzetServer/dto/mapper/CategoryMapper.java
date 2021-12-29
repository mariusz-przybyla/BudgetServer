package BudzetServer.BudzetServer.dto.mapper;


import BudzetServer.BudzetServer.dto.CategoryDto;
import BudzetServer.BudzetServer.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {

    CategoryDto toDto(Category entity);
    CategoryDto toEntity(CategoryDto dto);
}
