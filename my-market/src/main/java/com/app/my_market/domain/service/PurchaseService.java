package com.app.my_market.domain.service;

import com.app.my_market.domain.Purchase;
import com.app.my_market.domain.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    @Transactional
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}