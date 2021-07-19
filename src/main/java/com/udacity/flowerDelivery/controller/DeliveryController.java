package com.udacity.flowerDelivery.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.flowerDelivery.repository.entities.Delivery;
import com.udacity.flowerDelivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
   @Autowired
   DeliveryService deliveryService;

   @PostMapping
   public Delivery scheduleDelivery(@RequestBody Delivery delivery) {
       return deliveryService.update(delivery);
   }

   @GetMapping("/byName")
   public List<Delivery> findByName(String name){
      return deliveryService.findByName(name);
   }
}