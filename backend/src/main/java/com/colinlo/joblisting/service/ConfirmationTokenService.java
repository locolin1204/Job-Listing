package com.colinlo.joblisting.service;

import com.colinlo.joblisting.model.ConfirmationToken;
import com.colinlo.joblisting.model.User;

import java.util.Optional;

public interface ConfirmationTokenService {
    ConfirmationToken saveConfirmationToken(User user);
    ConfirmationToken getConfirmationToken(String token);
    void setTokenConfirmedAt(String token);
}
