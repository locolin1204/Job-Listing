package com.colinlo.joblisting.repository.impl;

import com.colinlo.joblisting.model.ConfirmationToken;
import com.colinlo.joblisting.repository.ConfirmationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    Logger logger = LoggerFactory.getLogger(ConfirmationTokenRepositoryImpl.class);
    String confirmationTokenCollectionName = "confirmationTokens";

    public ConfirmationToken createToken(ConfirmationToken confirmationToken) {
        logger.info(String.format("Created token for user: %s", confirmationToken.getUser().getEmail()));
        return mongoTemplate.insert(confirmationToken, confirmationTokenCollectionName);
    }

    public Optional<ConfirmationToken> getToken(String tokenKey) {
        Query query = new Query(Criteria.where("tokenKey").is(tokenKey));
        logger.info(String.format("Get token key: %s", tokenKey));
        return Optional.ofNullable(mongoTemplate.findOne(query, ConfirmationToken.class, confirmationTokenCollectionName));
    }

    public void updateToken(ConfirmationToken confirmationToken) {
        logger.info(String.format("Update token: %s", confirmationToken.getTokenKey()));
        mongoTemplate.save(confirmationToken, confirmationTokenCollectionName);
    }
}
