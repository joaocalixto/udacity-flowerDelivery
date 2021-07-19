package com.udacity.flowerDelivery.repository;

import com.udacity.flowerDelivery.repository.entities.Delivery;
import com.udacity.flowerDelivery.repository.entities.Plant;
import org.assertj.core.api.Assertions;
import org.h2.table.Plan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlantRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlantRepository plantRepository;

    @Test
    void findPlantByIdAndDeliveryCompleted() {

        Delivery delivery = getDelivery();
        Delivery deliveryActual = entityManager.persist(delivery);

        Plant plant = deliveryActual.getPlants().get(0);

        Plant plantByIdAndDeliveryCompleted = plantRepository.findPlantByIdAndDeliveryCompleted(plant.getId(), Boolean.FALSE);
        assertThat(plantByIdAndDeliveryCompleted).isNotNull();

        deliveryActual.setCompleted(Boolean.TRUE);

        Plant plantByIdAndDeliveryCompletedCompleted = plantRepository.findPlantByIdAndDeliveryCompleted(plant.getId(), Boolean.TRUE);

        assertThat(plantByIdAndDeliveryCompletedCompleted).isNotNull();


    }

    private Delivery getDelivery() {

        Delivery delivery = new Delivery();
        delivery.setAddress("Rua ABC");
        delivery.setName("Joao");

        List<Plant> plants = new ArrayList<>();
        Plant plant = getPlant();
        plant.setDelivery(delivery);
        plants.add(plant);
        delivery.setPlants(plants);

        return delivery;
    }

    @Test
    void findAllByPriceLessThanEqual() {

        Plant plantLower = getPlant();
        Plant plant2 = getPlant();
        plant2.setPrice(new BigDecimal(10d));

        entityManager.persist(plantLower);
        entityManager.persist(plant2);

        List<Plant> plantActual = plantRepository.findAllByPriceLessThanEqual(plantLower.getPrice());

        assertThat(plantActual).doesNotContain(plant2);
        assertThat(plantActual).contains(plantLower);


    }

    private Plant getPlant() {
        Plant plant = new Plant();
        plant.setName("Plant1");
        plant.setPrice(new BigDecimal(4d));

        return plant;
    }
}