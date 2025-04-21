package com.habib.microservices.product.repository;

import com.habib.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Custom query methods can be defined here if needed
}
