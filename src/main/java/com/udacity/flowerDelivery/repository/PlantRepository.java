package com.udacity.flowerDelivery.repository;

import com.udacity.flowerDelivery.repository.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    public Plant findPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    List<Plant> findAllByPriceLessThanEqual(BigDecimal amount);
}
