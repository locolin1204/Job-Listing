package com.colinlo.joblisting.service.impl;

import com.colinlo.joblisting.model.ConfirmationToken;
import com.colinlo.joblisting.model.User;
import com.colinlo.joblisting.repository.impl.ConfirmationTokenRepositoryImpl;
import com.colinlo.joblisting.service.ConfirmationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    Logger logger = LoggerFactory.getLogger(ConfirmationTokenServiceImpl.class);
    @Autowired
    private ConfirmationTokenRepositoryImpl confirmationTokenRepository;

    public ConfirmationToken saveConfirmationToken(User user) {
        String tokenKey = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                tokenKey,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        logger.info(String.format(
                "Saving token for user: %s",
                user.getEmail()
        ));
        return confirmationTokenRepository.createToken(confirmationToken);
    }

    public ConfirmationToken getConfirmationToken(String token) {
        return confirmationTokenRepository.getToken(token).orElseThrow(
                () -> new IllegalStateException("token not found")
        );
    }

    public void setTokenConfirmedAt(String token) {
        ConfirmationToken confirmationToken = this.getConfirmationToken(token);
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.updateToken(confirmationToken);
    }
}
