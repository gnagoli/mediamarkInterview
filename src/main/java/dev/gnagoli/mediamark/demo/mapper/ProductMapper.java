package dev.gnagoli.mediamark.demo.mapper;


import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.openapi.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    @Mapping(target = "id", source = "productId")
    ProductEntity toProductEntity(Product product);

    @Mapping(target = "productId", source = "id")
    Product toProduct(ProductEntity productEntity);

}
