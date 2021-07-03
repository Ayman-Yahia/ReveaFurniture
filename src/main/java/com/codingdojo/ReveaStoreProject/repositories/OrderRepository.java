package com.codingdojo.ReveaStoreProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.ReveaStoreProject.models.Order;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
