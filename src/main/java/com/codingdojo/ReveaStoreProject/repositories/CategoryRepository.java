package com.codingdojo.ReveaStoreProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.ReveaStoreProject.models.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}