package com.colinlo.joblisting.service;

import com.colinlo.joblisting.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService{
    User findUserByEmail(String email) throws UsernameNotFoundException;

    User createUser(User user);

    boolean checkUserEnabledByEmail(String email);

    User signUpAndGetUser(User user);

    void enableUser(String email);
}
