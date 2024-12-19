package com.app.my_market.persistance;

import com.app.my_market.domain.Product;
import com.app.my_market.domain.repository.ProductRepository;
import com.app.my_market.persistance.crud.ProductoCrudRepository;
import com.app.my_market.persistance.entity.Categoria;
import com.app.my_market.persistance.entity.Producto;
import com.app.my_market.persistance.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods-> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod));
    }
    @Override
    public Product save(Product product) {
        if (product.getCategoryId() != null && product.getCategoryId() > 0) {
            // Inicializa la categoría
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(product.getCategoryId());

            // Mapea el producto
            Producto producto = mapper.toProducto(product);
            producto.setIdProducto(null); // Asegura que el ID sea nulo para INSERT
            producto.setCategoria(categoria);

            // Guarda el producto
            Producto savedProducto = productoCrudRepository.save(producto);
            return mapper.toProduct(savedProducto);
        } else {
            throw new IllegalArgumentException("Category ID cannot be null or zero");
        }
    }




    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
