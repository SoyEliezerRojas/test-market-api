package com.app.my_market.persistance.mapper;

import com.app.my_market.domain.Product;
import com.app.my_market.persistance.entity.Categoria;
import com.app.my_market.persistance.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;


import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "idProducto", target = "productId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "precioVenta", target = "price")
    @Mapping(source = "cantidadStock", target = "stock")
    @Mapping(source = "estado", target = "active")
    @Mapping(source = "categoria", target = "category")
    Product toProduct(Producto producto);

    List<Product> toProducts(List<Producto> products);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    @Mapping(target = "categoria", expression = "java(toCategoria(product.getCategoryId()))")
    @Mapping(target = "idProducto", ignore = true) // Ignora el ID para nuevas entidades
    Producto toProducto(Product product);


    // Método auxiliar para inicializar Categoria
    default Categoria toCategoria(Integer categoryId) {
        if (categoryId == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoryId);
        return categoria;
    }
}
