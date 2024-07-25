package com.pet.care.pc.service;

import com.pet.care.pc.entitiy.shopping.product.Products;
import com.pet.care.pc.repository.ProductsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductsRepository repository;

  public List<Products> findAll() {
    return repository.findAll();
  }

  public Products findById(long id) {
    return repository.findById(id).orElseThrow();
  }
}
