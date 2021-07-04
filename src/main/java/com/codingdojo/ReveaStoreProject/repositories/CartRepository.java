package com.codingdojo.ReveaStoreProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.ReveaStoreProject.models.Cart;
import com.codingdojo.ReveaStoreProject.models.User;
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findByProduct_IdAndUser_Id (Long product_id,Long user_id);
	List<Cart> findByUser(User user);
	List<Cart> findByUser_IdAndOrdered(Long user_id,boolean ordered);


}
