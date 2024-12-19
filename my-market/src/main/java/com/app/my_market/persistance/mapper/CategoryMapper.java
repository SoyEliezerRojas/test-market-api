package com.app.my_market.persistance.mapper;

import com.app.my_market.domain.Category;
import com.app.my_market.persistance.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    // Mapeo de Categoria -> Category
    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "descripcion", target = "category")
    @Mapping(source = "estado", target = "active")
    Category toCategory(Categoria categoria);

    // Mapeo manual inverso (sin productos)
    default Categoria toCategoria(Category category) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(category.getCategoryId());
        categoria.setDescripcion(category.getCategory());
        categoria.setEstado(category.isActive());
        return categoria;
    }
}
