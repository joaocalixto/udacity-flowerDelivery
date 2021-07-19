package com.udacity.flowerDelivery.controller;

import com.sun.istack.NotNull;
import com.udacity.flowerDelivery.repository.entities.Plant;
import com.udacity.flowerDelivery.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plant")
public class PlantController {

   @Autowired
   private PlantService plantService;

   public PlantDTO getPlantDTO(String name){
       return convertEntityToPlantDTO(plantService.getPlantByName(name));
   }

   @GetMapping("/wasDelivered")
   public PlantDTO wasDelivered(@NotNull String plantId){
       return convertEntityToPlantDTO(plantService.wasDelivered(Long.parseLong(plantId)));
   }

   @GetMapping("/lesThan")
   public List<PlantDTO> lessThan(Double price){
       return plantService.findAllLessThanAmout(price).stream().map(plant -> convertEntityToPlantDTO(plant)).collect(Collectors.toList());
   }

   public Plant getFilteredPlant(String name){
       return plantService.getPlantByName(name);
   }

   public static PlantDTO convertEntityToPlantDTO(Plant plant){

       PlantDTO plantDTO = new PlantDTO();
       if(plant != null)
           BeanUtils.copyProperties(plant, plantDTO);
       return plantDTO;
   }
}