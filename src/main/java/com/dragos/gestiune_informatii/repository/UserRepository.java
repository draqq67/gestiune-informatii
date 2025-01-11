package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
//    @Query("SELECT User from User where User.username =:username")
    Optional<User> findByUsername(String username);
}
