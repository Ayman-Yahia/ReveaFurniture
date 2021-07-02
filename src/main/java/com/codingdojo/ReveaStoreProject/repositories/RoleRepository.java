package com.codingdojo.ReveaStoreProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.ReveaStoreProject.models.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
