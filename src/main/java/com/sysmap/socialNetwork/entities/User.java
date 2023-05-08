package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private String photoUri;

    public User(String name, String email){
        this.setId();
        this.name = name;
        this.email = email;
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this. id;
    }

}
