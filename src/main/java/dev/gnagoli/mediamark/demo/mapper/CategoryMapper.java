package dev.gnagoli.mediamark.demo.mapper;

import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import dev.gnagoli.mediamark.openapi.model.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface CategoryMapper {

    @Mapping(target = "id", source = "categoryId")
    CategoryEntity toCategory(Category category);

    @Mapping(target = "categoryId", source = "id")
    Category toEntity(CategoryEntity categoryEntity);

}
