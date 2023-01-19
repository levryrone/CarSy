package com.carsy.repository.user;

import com.carsy.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);
}
