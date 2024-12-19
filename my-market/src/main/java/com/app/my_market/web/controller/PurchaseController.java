package com.app.my_market.web.controller;


import com.app.my_market.domain.Purchase;
import com.app.my_market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        purchase.setDate(LocalDateTime.now());
        System.out.println("-----------------PURCHASE CONTROLLER---------------");
        System.out.println("purchaseId :"+purchase.getPurchaseId());
        System.out.println("Date :"+purchase.getDate());
        System.out.println("Comment :"+purchase.getComment());
        System.out.println("PaymentMethod :"+purchase.getPaymentMethod());
        System.out.println("Items :"+purchase.getItems());
        System.out.println("-----------------FIN PURCHASE CONTROLLER---------------");
        int retryCount = 10;
        while (retryCount > 0) {
            try {
                return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
            } catch (ObjectOptimisticLockingFailureException e) {
                retryCount--;
                if (retryCount == 0) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}