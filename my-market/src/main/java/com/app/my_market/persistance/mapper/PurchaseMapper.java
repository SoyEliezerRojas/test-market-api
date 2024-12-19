package com.app.my_market.persistance.mapper;

import com.app.my_market.domain.Purchase;
import com.app.my_market.domain.PurchaseItem;
import com.app.my_market.persistance.entity.Compra;
import com.app.my_market.persistance.entity.ComprasProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;


import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mapping(source = "idCompra", target = "purchaseId")
    @Mapping(source = "idCliente", target = "clientId")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "medioPago", target = "paymentMethod")
    @Mapping(source = "comentario", target = "comment")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "productos", target = "items")
    Purchase toPurchase(Compra compra);

    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra (Purchase purchase);
}

