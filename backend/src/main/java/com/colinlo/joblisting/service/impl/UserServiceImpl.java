package com.colinlo.joblisting.service.impl;

import com.colinlo.joblisting.model.User;
import com.colinlo.joblisting.repository.impl.UserRepositoryImpl;
import com.colinlo.joblisting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", email)));
    }

    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public boolean checkUserEnabledByEmail(String email) {
        User user = this.findUserByEmail(email);
        logger.info(String.format("Checking if user: %s is enabled", user.getEmail()));
        return user.isEnabled();
    }

    public User signUpAndGetUser(User user){
        Optional<User> optionalUser = userRepository.getUserByEmail(user.getEmail());
        if(optionalUser.isPresent()) {
            logger.info(String.format("User: %s already exist", user.getEmail()));
            return optionalUser.get();
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.createUser(user);
        }
    }

    public void enableUser(String email) {
        User user = userRepository
                .getUserByEmail(email)
                .orElseThrow(
                        () -> {
                            throw new IllegalStateException(String.format("User: %s does not exist", email));
                        }
                );
        user.setEnabled(true);
        userRepository.updateUser(user);
    }
}
