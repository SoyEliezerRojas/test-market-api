package com.app.my_market.domain.repository;

import com.app.my_market.domain.Product;
import java.util.List;
import java.util.Optional;


public interface ProductRepository {
    //Reglas que va a tener nuestro dominio al momento que un repositorio quiera acceder a Productos
    List<Product> getAll();
    Optional <List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
