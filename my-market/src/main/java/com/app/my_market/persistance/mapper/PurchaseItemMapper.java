package com.app.my_market.persistance.mapper;

import com.app.my_market.domain.PurchaseItem;
import com.app.my_market.persistance.entity.ComprasProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Named;


import java.awt.*;

@Mapper(componentModel = "spring", uses ={ProductMapper.class})
public interface PurchaseItemMapper {

    @Mapping(source = "id.idProducto", target = "productId")
    //@Mapping(target = "id.idCompra", ignore = true) // We will set this after persisting Compra
    @Mapping(source = "total", target = "total")
    @Mapping(source = "estado", target = "active")
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mapping(source = "productId", target = "id.idProducto")
    @Mapping(target = "id.idCompra", ignore = true)
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    ComprasProducto toComprasProducto(PurchaseItem item);

    @Named("booleanToString")
    default String booleanToString(boolean active) {
        return active ? "ACTIVO" : "INACTIVO";
    }



}

