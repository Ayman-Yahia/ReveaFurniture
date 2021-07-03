package com.codingdojo.ReveaStoreProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.ReveaStoreProject.models.Cart;
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
