package com.codingdojo.ReveaStoreProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.ReveaStoreProject.models.Category;
import com.codingdojo.ReveaStoreProject.models.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findAll();
	List<Product> findByCategory (Category category);

}
