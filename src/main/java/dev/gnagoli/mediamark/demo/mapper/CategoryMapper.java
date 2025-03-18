package dev.gnagoli.mediamark.demo.mapper;

import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import dev.gnagoli.mediamark.openapi.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "id", source = "categoryId")
    CategoryEntity toCategory(Category category);

    @Mapping(target = "categoryId", source = "id")
    Category toEntity(CategoryEntity categoryEntity);

}
