package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Like {
    private UUID userId;

    public Like(UUID userId) {
        this.userId = userId;
    }
}
