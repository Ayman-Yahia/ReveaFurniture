package com.codingdojo.ReveaStoreProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.ReveaStoreProject.models.Cart;
import com.codingdojo.ReveaStoreProject.models.Role;
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

	List<Cart> getCartByuserId(Long id);

	//Iterable<Role> findByUsername(String userName);

}
