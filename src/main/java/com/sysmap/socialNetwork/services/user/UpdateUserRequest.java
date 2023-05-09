package com.sysmap.socialNetwork.services.user;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserRequest {
    public UUID userId;
    public String name;
    public String email;
    public String password;

    public UpdateUserRequest(UUID userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
