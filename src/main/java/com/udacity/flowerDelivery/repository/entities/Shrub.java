package com.udacity.flowerDelivery.repository.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Shrub extends Plant {
   private int heightCm; //any reasonable unit of measurement is fine
   private int widthCm;

   /* getters and setters*/
}