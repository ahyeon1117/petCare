package com.pet.care.pc.service;

import com.pet.care.pc.dao.repository.ProductsRepository;
import com.pet.care.pc.entitiy.shopping.product.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductsRepository repository;

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(long id) {
    return repository.findById(id).orElseThrow();
  }
}
