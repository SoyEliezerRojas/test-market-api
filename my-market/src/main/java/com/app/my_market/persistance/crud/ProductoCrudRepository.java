package com.app.my_market.persistance.crud;

import com.app.my_market.persistance.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository <Producto, Integer>{
    //@Query(value = "SELECT * FROM PRODUCTOS WHERE ID_CATEGORIA = ?", nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    //findByIdCategoria
    Optional <List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
