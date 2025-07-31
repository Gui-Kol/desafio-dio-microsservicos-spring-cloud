// src/main/java/com/nttdata/challenge/orderservice/feignclient/ProductClient.java
package com.nttdata.challenge.order_service.feignclient;

import com.nttdata.challenge.order_service.model.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service") // << O nome DO SERVIÇO no Eureka! MUITO IMPORTANTE!
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDTO> getAllProducts();

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}