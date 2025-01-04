package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
