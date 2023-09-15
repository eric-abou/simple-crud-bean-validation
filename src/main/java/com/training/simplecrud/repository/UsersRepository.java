package com.training.simplecrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.simplecrud.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    
}
