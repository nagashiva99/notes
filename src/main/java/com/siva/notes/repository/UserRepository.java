package com.siva.notes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siva.notes.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserNameOrEmailAllIgnoreCase(String userName, String email);
}
