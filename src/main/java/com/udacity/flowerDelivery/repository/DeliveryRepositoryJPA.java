package com.udacity.flowerDelivery.repository;

import com.udacity.flowerDelivery.repository.entities.Delivery;
import com.udacity.flowerDelivery.repository.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepositoryJPA extends JpaRepository<Delivery, Long> {

}
