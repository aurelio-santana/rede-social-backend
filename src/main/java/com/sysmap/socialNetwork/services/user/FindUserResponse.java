package com.sysmap.socialNetwork.services.user;

import java.util.UUID;

//Model que retorna usuário
public class FindUserResponse {
    public UUID id;
    public String name;
    public String email;

    public FindUserResponse(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }




}
