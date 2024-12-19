package com.app.my_market.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PurchaseItem {
    private int productId;
    private int quantity;
    private double total;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Purchase purchase;

    public int getProductId() {
        return productId;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
