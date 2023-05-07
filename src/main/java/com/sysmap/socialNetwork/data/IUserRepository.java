package com.sysmap.socialNetwork.data;

import com.sysmap.socialNetwork.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
    //Repositório Mongo<Entidade usada, tipo do Id da entidade>
    //Optional<User> findUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(String userId);
}
