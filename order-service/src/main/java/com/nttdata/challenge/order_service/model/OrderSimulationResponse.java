// src/main/java/com/nttdata/challenge/orderservice/model/OrderSimulationResponse.java
package com.nttdata.challenge.order_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSimulationResponse {
    private String customerName;
    private List<ProductDTO> productsInOrder;
    private double totalAmount;
    private String message;
}