// src/main/java/com/nttdata/challenge/productservice/controller/ProductController.java
package com.nttdata.challenge.product_service.controller;

import com.nttdata.challenge.product_service.model.Product;
import com.nttdata.challenge.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Product> searchProductsByName(@RequestParam String name) {
        return productRepository.findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}