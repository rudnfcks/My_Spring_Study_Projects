package com.selfSpring.UserManagerment.repository;

import com.selfSpring.UserManagerment.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> getById(Long id);
    Optional<User> getByName(String name);
    List<User> getAll();
}
