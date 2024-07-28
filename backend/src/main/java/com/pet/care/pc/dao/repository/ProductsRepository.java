package com.pet.care.pc.dao.repository;

import com.pet.care.pc.entitiy.shopping.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {}
