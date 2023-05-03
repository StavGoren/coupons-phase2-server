package com.stav.server.controllers;

import com.stav.server.beans.Purchase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    @PostMapping
    public void createPurchase(@RequestBody Purchase purchase) {
        System.out.println(purchase);
    }

    @PutMapping
    public void updatePurchase(@RequestBody Purchase purchase) {
        System.out.println(purchase);
    }

    @GetMapping("{purchaseId}")
    public Purchase getPurchaseById(@PathVariable("purchaseId") int id) {
        return null;
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return null;
    }

    @DeleteMapping("{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") int id) {

    }
}
