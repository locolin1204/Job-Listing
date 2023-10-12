package com.colinlo.joblisting.service.impl;

import com.colinlo.joblisting.model.ConfirmationToken;
import com.colinlo.joblisting.model.RegisterRequest;
import com.colinlo.joblisting.model.Role;
import com.colinlo.joblisting.model.User;
import com.colinlo.joblisting.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private EmailValidatorServiceImpl emailValidator;

    public String registerUserAndCreateToken(RegisterRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(String.format(
                    "Email: %s is not valid",
                    request.getEmail()
            ));
        }
        if (userService.checkUserEnabledByEmail(request.getEmail())) {
            return String.format(
                    "User %s already enabled",
                    request.getEmail()
            );
        }
        User userTemplate = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password((request.getPassword()))
                .role(Role.USER)
                .build();

        User user = userService.signUpAndGetUser(userTemplate);
        ConfirmationToken confirmationToken = confirmationTokenService.saveConfirmationToken(user);
        String tokenKey = confirmationToken.getTokenKey();
        emailService.sendConfirmationEmail(
                request.getEmail(),
                request.getFirstname(),
                tokenKey
        );
        logger.info(String.format("Confirmation email sent to User: %s", request.getEmail()));
        return tokenKey;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken;
        try {
            confirmationToken = confirmationTokenService
                    .getConfirmationToken(token);
        } catch (Exception e){
            logger.error("Error fetching confirmation token", e);
            return "Error fetching token";
        }

        if (confirmationToken == null) {
            throw new IllegalStateException("token not exist");
        }

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            logger.info(String.format("Confirmation token expired"));
            return "token expired";
        }

        confirmationTokenService.setTokenConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail()
        );
        return "confirmed";
    }

    @Override
    public String resendToken(RegisterRequest request) {
        User user = userService.findUserByEmail(request.getEmail());
        return confirmationTokenService.saveConfirmationToken(user).getTokenKey();
    }
}
