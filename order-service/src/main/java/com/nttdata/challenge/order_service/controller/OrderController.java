// src/main/java/com/nttdata/challenge/orderservice/controller/OrderController.java
package com.nttdata.challenge.order_service.controller;

import com.nttdata.challenge.order_service.feignclient.ProductClient;
import com.nttdata.challenge.order_service.model.OrderSimulationRequest;
import com.nttdata.challenge.order_service.model.OrderSimulationResponse;
import com.nttdata.challenge.order_service.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/available-products")
    public ResponseEntity<List<ProductDTO>> getAvailableProducts() {
        try {
            List<ProductDTO> products = productClient.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.err.println("Erro ao buscar produtos do product-service: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/simulate")
    public ResponseEntity<OrderSimulationResponse> simulateOrder(@RequestBody OrderSimulationRequest request) {
        List<ProductDTO> productsInOrder = new ArrayList<>();
        double totalAmount = 0.0;
        String message = "Pedido simulado com sucesso!";

        if (request.getProductIds() == null || request.getProductIds().isEmpty()) {
            return ResponseEntity.badRequest().body(new OrderSimulationResponse(
                    request.getCustomerName(),
                    new ArrayList<>(),
                    0.0,
                    "Nenhum produto selecionado para o pedido."
            ));
        }

        for (Long productId : request.getProductIds()) {
            try {
                ProductDTO product = productClient.getProductById(productId);
                if (product != null) {
                    productsInOrder.add(product);
                    totalAmount += product.getPrice();
                } else {
                    message = "Alguns produtos não foram encontrados. Simulação parcial.";
                    System.out.println("Produto com ID " + productId + " não encontrado.");
                }
            } catch (Exception e) {
                message = "Erro ao buscar produto " + productId + ". Simulação parcial.";
                System.err.println("Erro ao chamar product-service para ID " + productId + ": " + e.getMessage());
            }
        }

        OrderSimulationResponse response = new OrderSimulationResponse(
                request.getCustomerName(),
                productsInOrder,
                totalAmount,
                message
        );
        return ResponseEntity.ok(response);
    }
}