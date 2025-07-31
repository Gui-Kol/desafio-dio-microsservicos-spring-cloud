// src/main/java/com/nttdata/challenge/orderservice/model/OrderSimulationRequest.java
package com.nttdata.challenge.order_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSimulationRequest {
    private List<Long> productIds;
    private String customerName;
}