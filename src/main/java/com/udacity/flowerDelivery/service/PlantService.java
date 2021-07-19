package com.udacity.flowerDelivery.service;

import com.udacity.flowerDelivery.repository.PlantRepository;
import com.udacity.flowerDelivery.repository.entities.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public Plant wasDelivered(Long plantId){
        return plantRepository.findPlantByIdAndDeliveryCompleted(plantId, Boolean.TRUE);
    }

    public List<Plant> findAllLessThanAmout(Double amout){
        return plantRepository.findAllByPriceLessThanEqual(BigDecimal.valueOf(amout));
    }


   public Plant getPlantByName(String name){
       return new Plant();
   }
}