// src/main/java/com/nttdata/challenge/productservice/repository/ProductRepository.java
package com.nttdata.challenge.product_service.repository;

import com.nttdata.challenge.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository já fornece métodos para CRUD (Create, Read, Update, Delete)
    // O primeiro tipo (Product) é a entidade, o segundo (Long) é o tipo do ID da entidade.
}