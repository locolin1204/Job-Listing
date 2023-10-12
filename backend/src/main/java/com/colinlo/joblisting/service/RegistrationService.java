package com.colinlo.joblisting.service;

import com.colinlo.joblisting.model.RegisterRequest;
import com.colinlo.joblisting.model.User;

public interface RegistrationService {
    String registerUserAndCreateToken(RegisterRequest request);
    String confirmToken(String token);
    String resendToken(RegisterRequest request);
}
