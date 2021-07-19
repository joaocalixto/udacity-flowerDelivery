package com.udacity.flowerDelivery.repository.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.flowerDelivery.controller.Views;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

// Uses InheritanceType.JOINED to store shared fields in 'plant' and unique fields
// in subclass tables
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
   @Id
   @GeneratedValue
   private Long id;

   @JsonView(Views.Public.class)
   @Nationalized // should use @Nationalized instead of @Type=nstring
   private String name;
   @JsonView(Views.Public.class)
   @Column(precision=12, scale=4)
   private BigDecimal price; // BigDecimal is the standard Java class for currency math

   @ManyToOne
   @JsonBackReference
   @JoinColumn(name = "delivery_id") //many plants can belong to one delivery
   private Delivery delivery;

   /* getters and setters*/
}