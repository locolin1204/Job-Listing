package com.colinlo.joblisting.service;

import com.colinlo.joblisting.model.*;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
