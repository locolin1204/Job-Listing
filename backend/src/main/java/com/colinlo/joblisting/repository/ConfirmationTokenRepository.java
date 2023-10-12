package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenRepository {

    ConfirmationToken createToken(ConfirmationToken confirmationToken);

    Optional<ConfirmationToken> getToken(String token);

    void updateToken(ConfirmationToken confirmationToken);
}
