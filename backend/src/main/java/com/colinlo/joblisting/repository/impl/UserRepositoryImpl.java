package com.colinlo.joblisting.repository.impl;

import com.colinlo.joblisting.controller.JobController;
import com.colinlo.joblisting.model.User;
import com.colinlo.joblisting.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    Logger logger = LoggerFactory.getLogger(JobController.class);
    String userCollectionName = "Users";

    @Override
    public Optional<User> findByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        logger.info(String.format("Getting User with email: %s", email));
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class, userCollectionName));
    }

    @Override
    public User createUser(User user) {
        logger.info(String.format("Created user with id: %s and email: %s", user.getId(), user.getEmail()));
        return mongoTemplate.insert(user, userCollectionName);
    }
}
