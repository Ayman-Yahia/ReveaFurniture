package com.codingdojo.ReveaStoreProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.ReveaStoreProject.models.Cart;
import com.codingdojo.ReveaStoreProject.models.User;
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
	List<Cart> findByProduct_IdAndUser_Id (Long product_id,Long user_id);
	List<Cart> findByUser(User user);
	List<Cart> findByUser_IdAndOrdered(Long user_id,boolean ordered);
	@Query("SELECT d FROM Cart d WHERE product_id = ?1 AND user_id=?2 AND ordered=?3")
    Cart getCartWhereIdAndUserAndNotOrederd(Long pid,Long uid,boolean ordered );


}
