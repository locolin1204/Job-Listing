package com.colinlo.joblisting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Document(collection = "confirmationToken")
public class ConfirmationToken {
    @Id
    private String id;
    @NonNull
    private String tokenKey;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    @DBRef
    private User user;

    public ConfirmationToken(
            String tokenKey,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            User user
    ) {
        this.tokenKey = tokenKey;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
