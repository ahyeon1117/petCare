package com.pet.care.pc.dao.repository;

import com.pet.care.pc.entitiy.shopping.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {}
