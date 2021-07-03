package com.codingdojo.ReveaStoreProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.ReveaStoreProject.models.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
