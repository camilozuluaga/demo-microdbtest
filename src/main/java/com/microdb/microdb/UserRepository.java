package com.microdb.microdb;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.microdb.microdb.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}