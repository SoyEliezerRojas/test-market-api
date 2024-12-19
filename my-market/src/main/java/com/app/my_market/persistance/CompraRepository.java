package com.app.my_market.persistance;

import com.app.my_market.domain.Purchase;
import com.app.my_market.domain.repository.PurchaseRepository;
import com.app.my_market.persistance.crud.CompraCrudRepository;
import com.app.my_market.persistance.entity.Compra;
import com.app.my_market.persistance.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);

        // Si el idCompra es 0, trátalo como una entidad nueva
        if (compra.getIdCompra() != null && compra.getIdCompra() == 0) {
            compra.setIdCompra(null);
        }

        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> producto.setCompra(compra));
        }

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }


}
