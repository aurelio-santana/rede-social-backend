package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Follow {
    private UUID id; //id para evitar gerar duplicata
    private UUID userId;
    private List<UUID> following; //seguindo
    private List<UUID> followers; //seguidores

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
