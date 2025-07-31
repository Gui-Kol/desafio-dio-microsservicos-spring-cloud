// src/main/java/com/nttdata/challenge/orderservice/model/ProductDTO.java
package com.nttdata.challenge.order_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
