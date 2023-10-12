package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByEmail(String email);
    User createUser(User user);
    void updateUser(User user);
}