package com.udacity.flowerDelivery.repository.projection;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecipientAndPriceDTO {


    private String name;
    private BigDecimal price;

}
