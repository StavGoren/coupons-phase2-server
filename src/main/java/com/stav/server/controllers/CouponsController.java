package com.stav.server.controllers;

import com.stav.server.beans.Coupon;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon) {
        System.out.println(coupon);
    }

    @PutMapping
    public void updateCoupon(@RequestBody Coupon coupon) {
        System.out.println(coupon);
    }

    @GetMapping("{couponId}")
    public Coupon getCoupon(@PathVariable("couponId") int id) {
        return null;
    }

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return null;
    }

    @DeleteMapping("{couponId}")
    public void deleteCoupon(@PathVariable("couponId") int id) {

    }
}
