package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Follow {
    private UUID id;
    private UUID userId;
    private List<UUID> following;
    private List<UUID> followers;

    public Follow(UUID userId) {
        this.setId();
        this.userId = userId;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this. id;
    }
}
